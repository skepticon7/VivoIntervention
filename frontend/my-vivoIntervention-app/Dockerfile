# Stage 1: Build the app
FROM node:22 AS build

WORKDIR /app
COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Stage 2: Serve the built app with Node
FROM node:22-alpine

WORKDIR /app

# Install a lightweight server
RUN npm install -g serve

# Copy built files
COPY --from=build /app/dist ./dist

EXPOSE 80

# Serve the app
CMD ["serve", "-s", "dist", "-l", "80"]
