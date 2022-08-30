CREATE TABLE IF NOT EXISTS logins(
			login_id INT PRIMARY KEY auto_increment,
			name VARCHAR(64) NOT NULL,
            username VARCHAR(64) NOT NULL,
            password VARCHAR(64) NOT NULL,
            isManager BOOLEAN NOT NULL
            );

CREATE TABLE IF NOT EXISTS routes(
			route_id INT PRIMARY KEY auto_increment,
            distance DECIMAL(8) NOT NULL,
            stop_count INT,
            login_id INT,
            FOREIGN KEY (login_id) REFERENCES logins(login_id)
            ON DELETE CASCADE
            );
            
CREATE TABLE IF NOT EXISTS addresses(
			address_id INT PRIMARY KEY auto_increment,
            address_line_one VARCHAR(64),
            address_line_two VARCHAR(64),
            city VARCHAR(50),
            postcode CHAR(8) NOT NULL
            );
            
CREATE TABLE IF NOT EXISTS orders(
			order_id INT PRIMARY KEY auto_increment,
            description VARCHAR(255) NOT NULL,
            address_id INT NOT NULL,
            FOREIGN KEY (address_id) REFERENCES addresses(address_id)
            );
            
CREATE TABLE IF NOT EXISTS routestops(
			routestop_id INT PRIMARY KEY auto_increment,
            stopnumber Int,
            order_id INT,
            estimated_time datetime NOT NULL,
            FOREIGN KEY (order_id) REFERENCES orders(order_id)
            );