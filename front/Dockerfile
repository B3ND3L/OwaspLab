FROM php:8.3-fpm

RUN apt-get update && apt-get install -y --no-install-recommends \
    libzip-dev \
    wget \
    git \
    unzip

RUN docker-php-ext-install zip pdo pdo_mysql

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

RUN composer global require leafs/cli

RUN ln -s /root/.composer/vendor/bin/leaf /usr/local/bin/leaf

ADD webapp.conf /etc/php/8.3/fpm/pool.d/

RUN apt-get purge -y g++ \
    && apt-get autoremove -y \
    && rm -rf /var/lib/apt/lists/* \
    && rm -rf /tmp/*

COPY src/* /var/www/owaspLab/

WORKDIR /var/www/owaspLab

RUN chown -R www-data:www-data /var/www/owaspLab

CMD ["php-fpm", "--nodaemonize", "--fpm-config", "/etc/php/8.3/fpm/pool.d/webapp.conf"]