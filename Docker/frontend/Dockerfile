FROM node:16 as build-stage

WORKDIR /app

COPY package*.json ./

RUN npm install

copy . .
#// compiles the application code into static files like html css
#--prod :to optimize the build for production (remove unused code or debug info)
#RUN npm run build -- --prod compiles your Angular application into optimized  \
#    static files suitable for production use.  \
#    These files are typically output into a dist directory within your project.
#=>These files are what you deploy to a web server or hosting service.
RUN npm run build --prod


#// nginx

FROM nginx:alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build-stage /app/dist/angularcourse /usr/share/nginx/html
EXPOSE 80
