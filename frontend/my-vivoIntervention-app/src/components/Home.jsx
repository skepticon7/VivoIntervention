import React,  { useState , useRef , useEffect } from 'react'
import Dashboard from './Dashboard'
import {
    BarChart3,
    Bell,
    Building2,
    Calendar,
    Download,
    Filter,
    Globe,
    Menu,
    Database,
    MoreHorizontal,
    Plus,
    RefreshCw,
    Search,
    TrendingUp,
    Users,
    Wifi,
    Wrench,
    X,
    Settings,
    LogOut,
    UserCog,
    Eye,
    ChevronDown, ServerCog, HardDriveDownload,
} from "lucide-react"
import { getInitials } from '../Utils/getInitials'
import {useAuth} from "../context/AuthContext.jsx";
import {
    Exports,
    Interventions,
    InterventionTypes, NewExportation,
    NewIntervention,
    Schedule,
    Sites,
    Technicians,
    UsersManagement
} from "./index.js";

import {vivoMiniLogo} from "../assets/index.js";
import UserViewUpdate from "@/components/UserViewUpdate.jsx";




const SideBarButton = ({selectedOption , setSelectedOption , title , Icon}) => {
    return (
        <button 
            onClick={() => setSelectedOption(title.toLowerCase())}   
          className={`flex w-full cursor-pointer py-3 px-3 rounded-md items-start  justify-start gap-3 hover:bg-dark-green transition-colors duration-200 ${selectedOption === title.toLowerCase() ? 'bg-dark-green' : 'bg-extradark-green'}`}>
           <Icon className='w-5 h-5 text-white stroke-[2]'/>
            <p className='text-white font-medium'>{title}</p>
        </button>

    )
}


const Home = () => {
    const [modalOpen , setModalOpen] = useState(false);
    const {user , logout} = useAuth();
    const role = user?.roles?.split("_")[1];
    const {selectedPage , setSelectedPage} = useAuth();
    const [searchQuery , setSearchQuery] = useState("");
    const [profileDropDown , setProfileDropDown] = useState(false);
    const [dataModalOpen , setDataModalOpen] = useState(false)
    const [profileModalState , setProfileModal] = useState({
        userId : null,
        isOpen : false,
        isEdit : false,
        userRole : ""
    })

    const handleOpenModal = ( ) => {
        setProfileModal(prev => ({
            ...prev,
            isEdit : true,
            isOpen: true
        }))
    }

    const handleCloseModal = () => {
        setProfileModal(prev => ({...prev, isOpen: false}));
    };

    useEffect(() => {
        setProfileModal(prev => ({
            ...prev,
            userId: user?.id,
            userRole: user?.roles?.split("_")[1]
        }))
    }, [user]);

    const renderCurrentPage = () => {
        switch(selectedPage) {
            case 'dashboard' : return <Dashboard/>
            case 'sites' : return <Sites/>
            case 'technicians' : return <Technicians/>
            case 'interventions' : return <Interventions/>
            case 'interventionType' : return <InterventionTypes/>
            case 'usersManagement' : return <UsersManagement/>
            case 'schedule' : return <Schedule/>
            case 'exports' : return <Exports/>
        }
    }

    const dropdownRef = useRef(null);

    useEffect(() => {
    const handleClickOutside = (event) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setProfileDropDown(false);
        }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
        document.removeEventListener('mousedown', handleClickOutside);
    };
    }, []);

    const handleLogout = () => {
        logout();
    }


  return (
    <div className='flex max-h-screen'>
        <aside className=' flex flex-col items-start justify-start sticky overflow-y-auto inset-y-0 left-0 w-76'>
            <div className='bg-dark-green w-full  px-6 py-3'>
                <div className='flex items-center space-x-3'>
                    <img className='w-9 h-9 rounded-md' src={vivoMiniLogo}/>
                    <div>
                        <span className='text-xl font-bold text-white'>Vivo Energy</span>
                        <p className='text-xs text-white/60'>IT Interventions Management System</p>
                    </div>
                </div>
            </div>
            <nav className='bg-extradark-green flex flex-col justify-between items-start w-full h-full px-6 py-4'>
                    <div className='space-y-2 w-full'>
                        <SideBarButton selectedOption={selectedPage} setSelectedOption={setSelectedPage} title={"Dashboard"} Icon={BarChart3}/>
                        <SideBarButton  selectedOption={selectedPage} setSelectedOption={setSelectedPage} title={"Interventions"} Icon={Wrench}/>
                        {(role === "SUPERUSER" || role === "SUPERVISOR") &&
                            <button
                                onClick={() => setSelectedPage("interventionType")}
                                className={`flex w-full cursor-pointer py-3 px-3 rounded-md items-start justify-start gap-3 hover:bg-dark-green transition-colors duration-200 ${selectedPage === 'interventionType' ? 'bg-dark-green' : null}`}>
                                <ServerCog className='w-5 h-5 text-white stroke-[2]'/>
                                <p className='text-white font-medium'>Intervention Type</p>
                            </button>}
                        <SideBarButton  selectedOption={selectedPage} setSelectedOption={setSelectedPage} title={"Sites"} Icon={Building2}/>
                        {(role === "SUPERVISOR" || role === "SUPERUSER") && <SideBarButton  selectedOption={selectedPage} setSelectedOption={setSelectedPage} title={"Technicians"} Icon={Users}/>}
                        {(role === "SUPERVISOR" || role === "SUPERUSER") && <SideBarButton  selectedOption={selectedPage} setSelectedOption={setSelectedPage} title={"Exports"} Icon={Database}/>}
                        <SideBarButton  selectedOption={selectedPage} setSelectedOption={setSelectedPage} title={"Schedule"} Icon={Calendar}/>

                        {(role === "SUPERVISOR" || role === "SUPERUSER") &&
                            <button
                                onClick={() => setSelectedPage("usersManagement")}
                                className={`flex w-full cursor-pointer py-3 px-3 rounded-md items-start justify-start gap-3 hover:bg-dark-green transition-colors duration-200 ${selectedPage === 'usersManagement' ? 'bg-dark-green' : null}`}>
                                <UserCog className='w-5 h-5 text-white stroke-[2]'/>
                                <p className='text-white font-medium'>User Management</p>
                            </button>}
                    </div>

                {(role === "SUPERVISOR" || role === "SUPERUSER") && (
                    <div className='p-3 flex flex-col items-start justify-start gap-2 w-full rounded-lg bg-dark-green'>
                        <p className='text-sm text-white font-semibold'>Quick Actions</p>
                        <button
                            onClick={() => setModalOpen(true)}
                            className='flex gap-5 w-full cursor-pointer rounded-md p-2 bg-main-green transition-colors duration-200 hover:bg-main-green/60'>
                            <Plus className='w-5 h-5 text-white'/>
                            <p className='text-sm font-semibold text-white'>New Intervention</p>
                        </button>

                        <button
                            onClick={() => setDataModalOpen(true)}
                            className='flex gap-5 w-full cursor-pointer hover:bg-main-green hover:border-transparent transition-colors duration-200 rounded-md p-2 border border-[1px] border-white bg-transparent'
                        >
                            <HardDriveDownload className='w-5 h-5 text-white'/>
                            <p className='text-sm font-semibold text-white'>Export Data</p>
                        </button>
                    </div>
                )}
            </nav>
        </aside>

        <div className='flex-1 flex-col overflow-y-auto h-screen'>
            <header className='bg-white border-b border-gray-300 border-[1px]    shadow-sm'>
                <div className='flex items-center justify-between px-6 py-4'>

                    <div className='flex items-center space-x-4'>
                        <div className='flex flex-col items-start justify-center gap-1'>
                            <h1 className='text-2xl font-bold text'>
                                {selectedPage === 'dashboard' && "Operations Dashboard"}
                                {selectedPage === 'interventions' && "Interventions"}
                                {selectedPage === 'interventionType' && "Intervention Types"}
                                {selectedPage === 'sites' && "Sites"}
                                {selectedPage === 'exports' && "Data Exports"}
                                {selectedPage === 'technicians' && "Technicians"}
                                {selectedPage === 'schedule' && "Schedule"}
                                {selectedPage === "usersManagement" && "Users management"}
                            </h1>
                            <p className='text-sm font-medium text-gray-500'>
                                {selectedPage === 'dashboard' && "Realtime monitoring & management"}
                                {selectedPage === 'exports' && "Track your CSV exports uploaded to OneDrive for Power BI usage."}
                                {selectedPage === 'interventions' && "Manage and track all technician interventions"}
                                {selectedPage === 'interventionType' && "Manage and track all intervention types"}
                                {selectedPage === 'sites' && "Manage all Vivo Energy locations and facilities"}
                                {selectedPage === 'technicians' && "Manage technician profiles and assignments"}
                                {selectedPage === 'schedule' && "Manage intervention schedules and technician assignments"}
                                {selectedPage === 'usersManagement' && "Manage user accounts, roles, and permissions"}
                            </p>
                        </div>
                    </div>

                    <div className='flex items-center space-x-4'>

                        <div className='relative border-black' ref={dropdownRef}>
                            <button
                             onClick={(e) => {
                                e.stopPropagation();
                                setProfileDropDown(!profileDropDown)
                             }} 
                             className='bg-main-green cursor-pointer flex  items-center justify-center w-12 h-12 rounded-full'>
                                {user && <p className='font-bold text-white text-lg'>{getInitials(user.fullName)}</p>}
                            </button>
                            {profileDropDown && (
                                <div 
                                 className={`absolute top-15 -right-30 border border-gray-300 border-[1px] rounded-lg p-3 w-56 shadow-xl bg-white flex flex-col justify-start items-start gap-2 ${profileDropDown ? 'dropdown-animate-down' : 'dropdown-animate-up'} `}
                                onClick={(e) => e.stopPropagation()}
                                 >
                                    <div className='flex flex-col items-start justify-start gap-1'>
                                        {user && <p className='font-semibold text-lg '>{user.fullName}</p>}
                                        {user && <p className='border border-[1px] border-gray-300 rounded-full px-2 text-sm font-semibold'>{user.roles.split("_")[1]}</p>}
                                    </div>
                                    <hr className='border border-[1px] border-gray-300 w-full mt-2'/>
                                    <div className='w-full flex flex-col items-start justify-start gap-1'>
                                        <button
                                            onClick={() =>{
                                                handleOpenModal()
                                                setProfileDropDown(false)
                                            }}
                                            className='w-full cursor-pointer flex items-center justify-start rounded-md py-1 px-2 gap-3 hover:bg-gray-100 bg-transparent transition-colors duration-200 w-full'
                                        >
                                            <Settings className='w-4 h-4 text-black'/>
                                            <p className='font-medium text-md'>Profile</p>
                                        </button>
                                        <button
                                            onClick={() => handleLogout()}
                                            className='w-full cursor-pointer flex items-center justify-start rounded-md py-1 px-2 gap-3 hover:bg-gray-100 bg-transparent transition-colors duration-200 w-full' >
                                            <LogOut className='w-4 h-4 text-black'/>
                                            <p className='font-medium text-md'>Log out</p>
                                        </button>
                                    </div>
                                </div>
                            )}
                        </div>

                    </div>
                </div>
            </header>
            <main className='p-6 flex-1 bg-gray-100 min-h-screen'>{renderCurrentPage()}</main>
            <NewIntervention isOpen={modalOpen} onClose={() => setModalOpen(false)}></NewIntervention>
            <NewExportation isOpen={dataModalOpen} onClose={() => setDataModalOpen(false)}/>
            <UserViewUpdate
                isEdit={profileModalState.isEdit}
                isOpen={profileModalState.isOpen}
                onClose={handleCloseModal}
                userRole={profileModalState.userRole}
                technicianId={profileModalState.userId}
            />
        </div>


    </div>
  )
}

export default Home



