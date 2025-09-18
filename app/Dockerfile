# Stage 1: build React app
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
# Workaround for old react-scripts + Node 20
ENV NODE_OPTIONS=--openssl-legacy-provider
RUN npm run build

# Stage 2: serve with Nginx
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
# Default Nginx conf replaced by our own to proxy /api
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
