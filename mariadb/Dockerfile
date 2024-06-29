FROM mariadb:latest

COPY sql/populate.sql /docker-entrypoint-initdb.d/

ENV MARIADB_ROOT_PASSWORD root_robust_password
ENV MARIADB_DATABASE owaspLab
ENV MARIADB_USER owaspLabUser
ENV MARIADB_PASSWORD user_robust_password
