# Utiliser l'image Nginx officielle basée sur Alpine
FROM nginx:alpine

# Copier la configuration personnalisée de Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Supprimer le fichier de configuration par défaut de Nginx
RUN rm /etc/nginx/conf.d/default.conf

# Copier le contenu statique de l'application frontend vers le répertoire de Nginx
COPY public/ /usr/share/nginx/html

# Exposer le port 80
EXPOSE 80

# Démarrer Nginx en avant-plan
CMD ["nginx", "-g", "daemon off;"]