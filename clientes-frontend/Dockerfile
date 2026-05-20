# syntax=docker/dockerfile:1.6

# ---------- Stage 1: build ----------
FROM node:20-alpine AS build
WORKDIR /workspace

COPY package.json ./
RUN npm install --legacy-peer-deps

COPY . .
RUN npm run build -- --configuration=production

# ---------- Stage 2: runtime ----------
FROM nginx:1.27-alpine

RUN rm -rf /usr/share/nginx/html/*
COPY --from=build /workspace/dist/sakai-ng/ /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

HEALTHCHECK --interval=20s --timeout=3s --start-period=10s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:80/ || exit 1

CMD ["nginx", "-g", "daemon off;"]
