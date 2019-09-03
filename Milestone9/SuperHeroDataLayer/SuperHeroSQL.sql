SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- -----------------------------------------------------
-- Database SuperHero_Sightings
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `SuperHero_Sightings` ;
CREATE DATABASE IF NOT EXISTS `SuperHero_Sightings` ;
USE `SuperHero_Sightings` ;

-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`SuperHeroes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`SuperHeroes` (
  `hero_id` INT NOT NULL AUTO_INCREMENT,
  `hero_name` VARCHAR(45) NOT NULL,
  `hero_description` VARCHAR(128) NOT NULL,
  `hero_superpower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`hero_id`),
  UNIQUE INDEX `hero_name_UNIQUE` (`hero_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Organizations` (
  `org_id` INT NOT NULL AUTO_INCREMENT,
  `org_name` VARCHAR(45) NOT NULL,
  `org_description` VARCHAR(128) NOT NULL,
  `org_address` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`org_id`),
  UNIQUE INDEX `org_name_UNIQUE` (`org_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Superhero_Organization_Affiliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Superhero_Organization_Affiliations` (
  `affiliation_id` INT NOT NULL AUTO_INCREMENT,
  `hero_id` INT NOT NULL,
  `org_id` INT NOT NULL,
  PRIMARY KEY (`affiliation_id`),
  INDEX `FK_HeroOrg_hero_id_idx` (`hero_id` ASC),
  INDEX `FK_HeroOrg_org_id_idx` (`org_id` ASC),
  CONSTRAINT `FK_HeroOrg_hero_id`
    FOREIGN KEY (`hero_id`)
    REFERENCES `SuperHero_Sightings`.`SuperHeroes` (`hero_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HeroOrg_org_id`
    FOREIGN KEY (`org_id`)
    REFERENCES `SuperHero_Sightings`.`Organizations` (`org_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Locations` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `location_name` VARCHAR(45) NOT NULL,
  `location_description` VARCHAR(128) NOT NULL,
  `location_address` VARCHAR(128) NOT NULL,
  `latitude` DECIMAL(10,4) NOT NULL,
  `longitude` DECIMAL(10,4) NOT NULL,
  PRIMARY KEY (`location_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Sightings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Sightings` (
  `sighting_id` INT NOT NULL AUTO_INCREMENT,
  `hero_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `sighting_time` DATE NOT NULL,
  PRIMARY KEY (`sighting_id`),
  INDEX `FK_Sightings_hero_id_idx` (`hero_id` ASC),
  INDEX `FK_Sightings_location_id_idx` (`location_id` ASC),
  CONSTRAINT `FK_Sightings_hero_id`
    FOREIGN KEY (`hero_id`)
    REFERENCES `SuperHero_Sightings`.`SuperHeroes` (`hero_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sightings_location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `SuperHero_Sightings`.`Locations` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Database SuperHero_Sightings_Test
-- -----------------------------------------------------

DROP DATABASE IF EXISTS `SuperHero_Sightings_Test` ;
CREATE DATABASE IF NOT EXISTS `SuperHero_Sightings_Test` ;
USE `SuperHero_Sightings_Test` ;

-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`SuperHeroes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`SuperHeroes` (
  `hero_id` INT NOT NULL AUTO_INCREMENT,
  `hero_name` VARCHAR(45) NOT NULL,
  `hero_description` VARCHAR(128) NOT NULL,
  `hero_superpower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`hero_id`),
  UNIQUE INDEX `hero_name_UNIQUE` (`hero_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Organizations` (
  `org_id` INT NOT NULL AUTO_INCREMENT,
  `org_name` VARCHAR(45) NOT NULL,
  `org_description` VARCHAR(128) NOT NULL,
  `org_address` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`org_id`),
  UNIQUE INDEX `org_name_UNIQUE` (`org_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Superhero_Organization_Affiliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Superhero_Organization_Affiliations` (
  `affiliation_id` INT NOT NULL AUTO_INCREMENT,
  `hero_id` INT NOT NULL,
  `org_id` INT NOT NULL,
  PRIMARY KEY (`affiliation_id`),
  INDEX `FK_HeroOrg_hero_id_idx` (`hero_id` ASC),
  INDEX `FK_HeroOrg_org_id_idx` (`org_id` ASC),
  CONSTRAINT `FK_HeroOrg_hero_id`
    FOREIGN KEY (`hero_id`)
    REFERENCES `SuperHero_Sightings_Test`.`SuperHeroes` (`hero_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HeroOrg_org_id`
    FOREIGN KEY (`org_id`)
    REFERENCES `SuperHero_Sightings_Test`.`Organizations` (`org_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Locations` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `location_name` VARCHAR(45) NOT NULL,
  `location_description` VARCHAR(128) NOT NULL,
  `location_address` VARCHAR(128) NOT NULL,
  `latitude` DECIMAL(10,4) NOT NULL,
  `longitude` DECIMAL(10,4) NOT NULL,
  PRIMARY KEY (`location_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Sightings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Sightings` (
  `sighting_id` INT NOT NULL AUTO_INCREMENT,
  `hero_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `sighting_time` DATE NOT NULL,
  PRIMARY KEY (`sighting_id`),
  INDEX `FK_Sightings_hero_id_idx` (`hero_id` ASC),
  INDEX `FK_Sightings_location_id_idx` (`location_id` ASC),
  CONSTRAINT `FK_Sightings_hero_id`
    FOREIGN KEY (`hero_id`)
    REFERENCES `SuperHero_Sightings_Test`.`SuperHeroes` (`hero_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sightings_location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `SuperHero_Sightings_Test`.`Locations` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;