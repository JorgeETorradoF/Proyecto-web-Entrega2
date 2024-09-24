#imagen de nodeJS
FROM node:alpine
#directorio de trabajo
WORKDIR /usr/src/app
#copiamos todo
COPY package*.json ./

# Instalamos las dependencias (esto usa el cache si no hay cambios en package.json)
RUN npm install

# Instalamos Angular CLI globalmente
RUN npm install -g @angular/cli

# Copiamos el resto
COPY . .

CMD ["ng", "serve", "--host", "0.0.0.0"]