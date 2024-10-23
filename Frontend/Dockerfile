FROM node:18 AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod


FROM nginx:latest

COPY --from=build /app/dist/frontend /usr/share/nginx/html
EXPOSE 8088
CMD ["nginx", "-g", "daemon off;"]
