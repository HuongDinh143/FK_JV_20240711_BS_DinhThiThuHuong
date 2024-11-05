create database quanlybanhang;
use quanlybanhang;

create table Categories
(
    category_id     int primary key auto_increment,
    category_name   varchar(50) not null unique,
    category_status bit default 1
);

create table Products
(
    product_id    int primary key auto_increment,
    product_name  varchar(20),
    stock         int    not null,
    cost_price    double not null check (cost_price > 0),
    selling_price double not null check (selling_price > 0),
    created_at    DATETIME default current_timestamp,
    category_id   int    not null,
    foreign key (category_id) references Categories (category_id)
);
DELIMITER &&
CREATE PROCEDURE find_all_categories()
BEGIN
    SELECT * FROM Categories ;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE find_all_categories_active()
BEGIN
    SELECT * FROM Categories WHERE category_status = 0;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE find_categories_by_id(cat_id_in int)
BEGIN
    SELECT * FROM Categories WHERE category_id = cat_id_in;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE save_category(
    cat_name_in varchar(50),
    cat_status_in bit
)
BEGIN
    INSERT INTO Categories
    SET category_name   = cat_name_in,
        category_status = cat_status_in;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE update_category(
    cat_id_in int,
    cat_name_in varchar(50),
    cat_status_in bit
)
BEGIN
    UPDATE Categories
    SET category_name   = cat_name_in,
        category_status = cat_status_in
    WHERE category_id = cat_id_in;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE delete_category(cat_id_in int)
BEGIN
    UPDATE Categories
    SET category_status = 1
    WHERE category_id = cat_id_in;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE statistic_product_by_cat(
    cat_id_in int,
    out cnt_product int)
BEGIN
    SET cnt_product = (SELECT COUNT(product_id)
                       FROM Products
                       WHERE category_id = cat_id_in);
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE find_all_product()
BEGIN
   SELECT * FROM products;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE find_product_by_id(pr_id_in int)
BEGIN
    SELECT * FROM products WHERE product_id = pr_id_in;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE delete_product(pr_id_in int)
BEGIN
    DELETE FROM Products WHERE product_id=pr_id_in;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE findProductByPrice(selling_price_a double, selling_price_b double)
BEGIN
    SELECT * FROM products where selling_price>=selling_price_a and selling_price<=selling_price_b;
END &&
DELIMITER ;












