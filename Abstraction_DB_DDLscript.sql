SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Abstraction
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS Abstraction;
CREATE SCHEMA IF NOT EXISTS `Abstraction` DEFAULT CHARACTER SET utf8 ;
DROP USER IF EXISTS 'abstraction_SU'@'%';
flush privileges;

-- -----------------------------------------------------
-- Schema Abstraction
-- -----------------------------------------------------
CREATE USER 'abstraction_SU'@'%' IDENTIFIED BY 'Abstraction';
GRANT INSERT, UPDATE, DELETE, SELECT ON `Abstraction`.* TO 'abstraction_SU'@'%';

-- -----------------------------------------------------
-- Schema Abstraction
-- -----------------------------------------------------
USE `Abstraction` ;

-- -----------------------------------------------------
-- Table `Abstraction`.`Producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Abstraction`.`Producto` ;

CREATE TABLE IF NOT EXISTS `Abstraction`.`Producto` (
  `referencia` INT UNSIGNED NOT NULL,
  `nombre` VARCHAR(245) NOT NULL,
  `precio` DECIMAL(20) NOT NULL,
  `existencias` INT NOT NULL,
  `descripcion` VARCHAR(245) NULL,
  `archivado` INT NOT NULL,
  `pathImagen` VARCHAR(245) NULL,
  PRIMARY KEY (`referencia`),
  UNIQUE INDEX `referencia_UNIQUE` (`referencia` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abstraction`.`Cotizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Abstraction`.`Cotizacion` ;

CREATE TABLE IF NOT EXISTS `Abstraction`.`Cotizacion` (
  `numero` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(245) NOT NULL,
  `fecha` DATE NOT NULL,
  `precioTotal` DECIMAL(20) NOT NULL,
  `nombreCliente` VARCHAR(245) NOT NULL,
  `archivado` INT NOT NULL,
  PRIMARY KEY (`numero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abstraction`.`CotizacionProducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Abstraction`.`CotizacionProducto` ;

CREATE TABLE IF NOT EXISTS `Abstraction`.`CotizacionProducto` (
  `Cotizacion_numero` INT NOT NULL,
  `Producto_referencia` INT UNSIGNED NOT NULL,
  `cantidad` INT NOT NULL,
  INDEX `fk_CotizacionProducto_Cotizacion_idx` (`Cotizacion_numero` ASC) ,
  INDEX `fk_CotizacionProducto_Producto1_idx` (`Producto_referencia` ASC) ,
  PRIMARY KEY (`Cotizacion_numero`, `Producto_referencia`),
  CONSTRAINT `fk_CotizacionProducto_Cotizacion`
    FOREIGN KEY (`Cotizacion_numero`)
    REFERENCES `Abstraction`.`Cotizacion` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CotizacionProducto_Producto1`
    FOREIGN KEY (`Producto_referencia`)
    REFERENCES `Abstraction`.`Producto` (`referencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abstraction`.`Pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Abstraction`.`Pedido` ;

CREATE TABLE IF NOT EXISTS `Abstraction`.`Pedido` (
  `numero` INT NOT NULL AUTO_INCREMENT,
  `Cotizacion_numero` INT NOT NULL,
  `nombre` VARCHAR(245) NOT NULL,
  `fecha` DATE NOT NULL,
  `valor` DECIMAL(20) NOT NULL,
  `estado` VARCHAR(245) NOT NULL,
  `archivado` INT NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_Pedido_Cotizacion1_idx` (`Cotizacion_numero` ASC) ,
  CONSTRAINT `fk_Pedido_Cotizacion1`
    FOREIGN KEY (`Cotizacion_numero`)
    REFERENCES `Abstraction`.`Cotizacion` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abstraction`.`Factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Abstraction`.`Factura` ;

CREATE TABLE IF NOT EXISTS `Abstraction`.`Factura` (
  `numero` VARCHAR(45) NOT NULL,
  `Pedido_numero` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `valor` DECIMAL(20) NOT NULL,
  `abonosTotal` DECIMAL(20) NOT NULL,
  `archivado` INT NOT NULL,
  PRIMARY KEY (`numero`, `Pedido_numero`),
  CONSTRAINT `fk_Factura_Pedido1`
    FOREIGN KEY (`Pedido_numero`)
    REFERENCES `Abstraction`.`Pedido` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
