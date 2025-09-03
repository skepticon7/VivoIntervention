import React from "react";

const OverviewCard = ({Icon , title , description , background , button}) => {
    return (
        <div className='flex p-6 rounded-lg bg-white border-[1px] border-gray-300 items-center justify-between'>
            <div >
                <p className='text-gray-500 text-sm font-regular'>{title}</p>
                <p className='text-black font-bold text-xl'>{description}</p>
            </div>
            <div className={`flex items-center justify-center p-3 ${background}  rounded-md`}>
                <Icon className={`w-6 h-6 ${button}`}/>
            </div>
        </div>
    )
}

export default OverviewCard;