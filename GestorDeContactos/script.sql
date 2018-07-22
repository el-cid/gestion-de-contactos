CREATE DATABASE Gestor_Contactos;
CREATE USER 'gestor_user'@'localhost' IDENTIFIED BY 'aDsI_pAsSwOrD_0';
GRANT ALL ON Gestor_Contactos.* TO 'gestor_user'@'localhost';
FLUSH PRIVILEGES;
USE Gestor_Contactos;
 
CREATE TABLE Usuario(
    usuario_ID int(10)AUTO_INCREMENT NOT NULL,
    usuario_name varchar(50) UNIQUE NOT NULL,
    usuario_password varchar(50) NOT NULL,
    PRIMARY KEY (usuario_ID)
);

INSERT INTO Usuario (usuario_name,usuario_password) VALUES ('panchito25','loveupanchita');

CREATE TABLE Name(
    name_ID int(10)AUTO_INCREMENT NOT NULL,
    name_givenName varchar(50),
    name_familyName varchar(50),
    name_additionalName varchar(50),
    name_honorificPrefix varchar(10),
    name_honorificSuffix varchar(10),
    PRIMARY KEY (name_ID)
);
 
CREATE TABLE Contact(
    contact_ID int(10)AUTO_INCREMENT NOT NULL,
    contact_formattedName varchar(50),
    contact_birthday varchar(10),
    name_ID int(10),
    usuario_ID int(10),
    PRIMARY KEY (contact_ID),
    FOREIGN KEY (name_ID) REFERENCES Name(name_ID) ON DELETE CASCADE,
    FOREIGN KEY (usuario_ID) REFERENCES Usuario(usuario_ID) ON DELETE CASCADE
);  
 
CREATE TABLE Email(
    email_ID int(10)AUTO_INCREMENT NOT NULL,
    email_value varchar(55),
    email_type varchar(30),
    contact_ID int(10),
    PRIMARY KEY (email_ID),
    FOREIGN KEY (contact_ID) REFERENCES Contact(contact_ID) ON DELETE CASCADE
);
 

 
CREATE TABLE Telephone(
    telephone_ID int(10)AUTO_INCREMENT NOT NULL,
    telephone_value varchar(55),
    telephone_type varchar(30),
    contact_ID int(10),
    PRIMARY KEY (telephone_ID),
    FOREIGN KEY (contact_ID) REFERENCES Contact(contact_ID) ON DELETE CASCADE
);
 
CREATE TABLE Adress(
    adress_ID int(10)AUTO_INCREMENT NOT NULL,
    adress_type varchar(15),
    adress_postOffice varchar(50),
    address_extendedAdress varchar(50),
    address_street varchar(50),
    address_locality varchar(50),
    address_region varchar(50),
    address_postalCode varchar(10),
    address_Country varchar(50),
    contact_ID int(10),
    PRIMARY KEY (adress_ID),
    FOREIGN KEY (contact_ID) REFERENCES Contact(contact_id) ON DELETE CASCADE
);
