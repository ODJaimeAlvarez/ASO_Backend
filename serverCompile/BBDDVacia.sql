-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.9-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para proyectoaso
CREATE DATABASE IF NOT EXISTS `proyectoaso` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `proyectoaso`;

-- Volcando estructura para evento proyectoaso.check_jornada_abierta
DELIMITER //
CREATE EVENT `check_jornada_abierta` ON SCHEDULE EVERY 24 HOUR STARTS '2021-06-12 02:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
DECLARE abierta BOOLEAN;
DECLARE id INTEGER;
DECLARE cursor_jornada CURSOR FOR SELECT id_jornada,iniciada FROM jornada ;

	OPEN cursor_jornada;
	
	LOOP
		fetch cursor_jornada INTO id,abierta;
		
		IF abierta=1 then
			UPDATE jornada SET iniciada=0 WHERE id=jornada.id_jornada;
					
		END IF;
		
	END LOOP;
close cursor_jornada;
END//
DELIMITER ;

-- Volcando estructura para tabla proyectoaso.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `apellido_1` varchar(80) NOT NULL,
  `apellido_2` varchar(80) NOT NULL,
  `ciudad` varchar(90) DEFAULT NULL,
  `cp` varchar(10) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `empresa` varchar(100) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `pais` varchar(50) DEFAULT NULL,
  `telefono` varchar(11) DEFAULT NULL,
  `fk_id_foto` int(11) DEFAULT NULL,
  `usuario_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `FKqlu1rb59j9c6xve7a7cmkfifw` (`fk_id_foto`),
  KEY `FKs54fqokuf1xawevbirtv9o937` (`usuario_id_usuario`),
  CONSTRAINT `FKqlu1rb59j9c6xve7a7cmkfifw` FOREIGN KEY (`fk_id_foto`) REFERENCES `foto_user` (`id_foto`),
  CONSTRAINT `FKs54fqokuf1xawevbirtv9o937` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.empleado
CREATE TABLE IF NOT EXISTS `empleado` (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `apellido_1` varchar(80) NOT NULL,
  `apellido_2` varchar(80) NOT NULL,
  `ciudad` varchar(90) NOT NULL,
  `cp` varchar(10) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(200) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `pais` varchar(50) NOT NULL,
  `puesto` varchar(80) NOT NULL,
  `telefono` varchar(11) NOT NULL,
  `fk_id_foto` int(11) DEFAULT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `FK230dxovcu2mop13nu4bi9xamw` (`fk_id_foto`),
  KEY `FKi22gtpvcn07cso3o0k7vg91lb` (`fk_id_usuario`),
  CONSTRAINT `FK230dxovcu2mop13nu4bi9xamw` FOREIGN KEY (`fk_id_foto`) REFERENCES `foto_user` (`id_foto`),
  CONSTRAINT `FKi22gtpvcn07cso3o0k7vg91lb` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

INSERT INTO `proyectoaso`.`empleado` (`apellido_1`, `apellido_2`, `ciudad`, `cp`, `descripcion`, `direccion`, `nombre`, `pais`, `puesto`, `telefono`, `fk_id_usuario`) VALUES ('admin', 'admin', 'null', 'null', 'null', 'null', 'admin', 'España', 'Director', 'null', '1');

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.fichero
CREATE TABLE IF NOT EXISTS `fichero` (
  `id_fichero` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_mod` datetime NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `uri` varchar(200) NOT NULL,
  `fk_id_proyecto` int(11) NOT NULL,
  PRIMARY KEY (`id_fichero`),
  UNIQUE KEY `UK7aw74hvog1udxrge6tly1yccb` (`uri`,`nombre`),
  KEY `FKk0fom8n0tbyah58a397s272oe` (`fk_id_proyecto`),
  CONSTRAINT `FKk0fom8n0tbyah58a397s272oe` FOREIGN KEY (`fk_id_proyecto`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.foto_user
CREATE TABLE IF NOT EXISTS `foto_user` (
  `id_foto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `uri` varchar(200) NOT NULL,
  PRIMARY KEY (`id_foto`),
  UNIQUE KEY `UKsmq88eya5apnls0ujq8thw6yh` (`uri`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.jornada
CREATE TABLE IF NOT EXISTS `jornada` (
  `id_jornada` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `hora_fin` time DEFAULT NULL,
  `hora_inicio` time NOT NULL,
  `iniciada` bit(1) NOT NULL,
  `fk_id_empleado` int(11) NOT NULL,
  PRIMARY KEY (`id_jornada`),
  KEY `FKo4fy375thn9ot1kg2k9ptqm9x` (`fk_id_empleado`),
  CONSTRAINT `FKo4fy375thn9ot1kg2k9ptqm9x` FOREIGN KEY (`fk_id_empleado`) REFERENCES `empleado` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.log_cliente_dia
CREATE TABLE IF NOT EXISTS `log_cliente_dia` (
  `id_log_cliente_dia` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad_clientes` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_log_cliente_dia`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.log_creacion_usuario
CREATE TABLE IF NOT EXISTS `log_creacion_usuario` (
  `id_log_creacion_usuario` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `id_usuario` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_log_creacion_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.log_empleado_dia
CREATE TABLE IF NOT EXISTS `log_empleado_dia` (
  `id_log_empleado_dia` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `cantidad_empleados` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_log_empleado_dia`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.nota
CREATE TABLE IF NOT EXISTS `nota` (
  `id_nota` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `nota` varchar(255) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_nota`),
  KEY `FKm275ipqdn3pxrt6ndjnkahncv` (`fk_id_usuario`),
  CONSTRAINT `FKm275ipqdn3pxrt6ndjnkahncv` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.proyecto
CREATE TABLE IF NOT EXISTS `proyecto` (
  `id_proyecto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `progreso` enum('iniciado','en_curso','finalizado','aceptado') NOT NULL,
  PRIMARY KEY (`id_proyecto`),
  UNIQUE KEY `UKgvu9yy8rcb4h2hr1f6av0f0ml` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.proyecto_usuario
CREATE TABLE IF NOT EXISTS `proyecto_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_proyecto` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi9ccw0ei0hfb8e2y34og8da9u` (`fk_id_proyecto`),
  KEY `FKjb8qwqpav57aqevo4qgs666ae` (`fk_id_usuario`),
  CONSTRAINT `FKi9ccw0ei0hfb8e2y34og8da9u` FOREIGN KEY (`fk_id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  CONSTRAINT `FKjb8qwqpav57aqevo4qgs666ae` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(100) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `UKgidd9huji2j14xop37v9dc7li` (`rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `rol` (`id_rol`, `rol`) VALUES
	(2, 'CLIENTE'),
	(1, 'DIRECTOR'),
	(3, 'EMPLEADO');
-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.rol_usuario
CREATE TABLE IF NOT EXISTS `rol_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_rol` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo5mi2n1ttv7ndov37bdggoq8k` (`fk_id_rol`),
  KEY `FK18unj0n2p2hhicmvawfw407xp` (`fk_id_usuario`),
  CONSTRAINT `FK18unj0n2p2hhicmvawfw407xp` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKo5mi2n1ttv7ndov37bdggoq8k` FOREIGN KEY (`fk_id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

INSERT INTO `rol_usuario` (`fk_id_rol`, `fk_id_usuario`) VALUES
	('1','1', '1');
-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla proyectoaso.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `correo_corporativo` varchar(80) NOT NULL,
  `password_usuario` varchar(100) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

INSERT INTO `usuario` (`id_usuario`,`activo`, `correo_corporativo`, `password_usuario`) VALUES
	('1',b'1', 'admin@aso.codes', '$2a$10$s1XDMgav9YfkEcVvC/T8Hu2e0D3A9bx8LfToDVncacbEq90vX347u');
-- La exportación de datos fue deseleccionada.

-- Volcando estructura para disparador proyectoaso.check_jornada_iniciada
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER check_jornada_iniciada before INSERT ON jornada FOR EACH ROW
BEGIN

DECLARE Viniciada TINYINT(1);
DECLARE VfechaMax DATE;
DECLARE VfechaCount INT;
DECLARE VhoraMax TIME;
DECLARE VhoraNull INT;
DECLARE ViniciadaMax INT;
BEGIN 
	
	
	
	SELECT max(fecha) into VfechaMax FROM jornada WHERE fk_id_empleado = NEW.fk_id_empleado;
	
	SELECT COUNT(fecha)into VfechaCount FROM jornada WHERE fecha=VfechaMax;
	
	IF VfechaCount =1 or VfechaCount=0 then
	
	
		SELECT iniciada INTO Viniciada FROM jornada WHERE fk_id_empleado= NEW.fk_id_empleado AND fecha = VfechaMax ;
	
		
	
		IF Viniciada=1 THEN
			signal sqlstate '45000' set message_text = 'Error al abrir una jornada ya que ya se había iniciada una jornada con el mismo usuario';
	

	END IF;
	
	ELSEIF VfechaCount>1 then
	
		SELECT COUNT(hora_fin) into VhoraNull FROM jornada WHERE hora_fin= NULL;

        if VhoraNull = 1 then

                signal sqlstate '45000' set message_text = 'Error al abrir una jornada ya hay una jornada iniciada';

        ELSEIF VhoraNull= 0 then

                SELECT max(hora_fin) INTO VhoraMax FROM jornada;
                SELECT iniciada INTO ViniciadaMax FROM jornada WHERE hora_fin = VhoraMax AND fecha =VfechaMax;

                IF ViniciadaMax= 1 then

                    signal sqlstate '45000' set message_text = 'Error al abrir una jornada ya que ya se había iniciada una jornada con el mismo usuario';

                END if;
        END IF;
	
	END IF;

END;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador proyectoaso.log_cliente_dia
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `log_cliente_dia` BEFORE INSERT ON `cliente` FOR EACH ROW BEGIN
DECLARE Vfecha DATE;
DECLARE Vcantidad INT(10);

BEGIN 
	
	SELECT fecha INTO Vfecha FROM log_cliente_dia WHERE fecha=DATE_FORMAT(NOW(),'%Y-%m-%d');
	SELECT cantidad_clientes INTO Vcantidad from log_cliente_dia WHERE fecha=DATE_FORMAT(NOW(),'%Y-%m-%d');
	
	IF Vfecha is NULL then
	
	INSERT INTO log_cliente_dia (fecha,cantidad_clientes) VALUES (DATE_FORMAT(NOW(),'%Y-%m-%d'),1);
		
	ELSE 
	
	UPDATE log_cliente_dia SET cantidad_clientes= Vcantidad+1 WHERE fecha= DATE_FORMAT(NOW(),'%Y-%m-%d');
	
	END IF;

END;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador proyectoaso.log_empleado_dia
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER log_empleado_dia after INSERT ON empleado FOR EACH ROW
BEGIN

DECLARE Vfecha DATE;
DECLARE Vcantidad INT(10);

BEGIN 
	
	SELECT fecha INTO Vfecha FROM log_empleado_dia WHERE fecha=DATE_FORMAT(NOW(),'%Y-%m-%d');
	SELECT cantidad_empleados INTO Vcantidad from log_empleado_dia WHERE fecha=DATE_FORMAT(NOW(),'%Y-%m-%d');
	
	IF Vfecha is NULL then
	
	INSERT INTO log_empleado_dia (fecha,cantidad_empleados) VALUES (DATE_FORMAT(NOW(),'%Y-%m-%d'),1);
		
	ELSE 
	
	UPDATE log_empleado_dia SET cantidad_empleados= Vcantidad+1 WHERE fecha= DATE_FORMAT(NOW(),'%Y-%m-%d');
	
	END IF;

END;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador proyectoaso.log_usuario
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `log_usuario` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN

INSERT INTO log_creacion_usuario (fecha,id_usuario) VALUES (NOW(),NEW.id_usuario);

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
