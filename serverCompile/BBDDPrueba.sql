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
SET GLOBAL event_scheduler = ON;
-- Volcando estructura para evento proyectoaso.check_jornada_abierta
DELIMITER //
CREATE EVENT `check_jornada_abierta` ON SCHEDULE EVERY 24 HOUR STARTS '2021-06-12 02:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
DECLARE abierta BOOLEAN;
DECLARE id INTEGER;
DECLARE Vfecha DATE;
DECLARE DiffHoras INTEGER;
DECLARE VEmple INTEGER;
DECLARE cursor_jornada CURSOR FOR SELECT id_jornada,iniciada,DATEDIFF(NOW(),fecha)*24,fk_id_empleado FROM jornada ;

	OPEN cursor_jornada;
	
	LOOP
		fetch cursor_jornada INTO id,abierta,DiffHoras,VEmple;
		
		IF abierta=1 then
		
			IF DiffHoras>10 && DiffHoras<=24 then
				INSERT INTO log_cambio_jornada(Tipo_mensaje,fecha,Empleado) VALUES ('Aviso',NOW(),VEmple);
		
			ELSEIF DiffHoras>24 then
			
					INSERT INTO log_cambio_jornada(Tipo_mensaje,fecha,Empleado) VALUES ('Cambiado',NOW(),VEmple);
					UPDATE jornada SET iniciada=0 WHERE id=jornada.id_jornada;
			
			END IF;
			
		
					
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



-- Volcando datos para la tabla proyectoaso.cliente: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `apellido_1`, `apellido_2`, `ciudad`, `cp`, `descripcion`, `direccion`, `empresa`, `nombre`, `pais`, `telefono`, `fk_id_foto`, `usuario_id_usuario`) VALUES
	(5, 'Garcia', 'Hernandez', 'Madrid', '28033', '', 'Calle 4', 'AMD', 'Jose', 'Spain', '607665413', NULL, 7),
	(6, 'Murillo', 'Sanchez', 'Barcelona', '33033', NULL, 'Calle Infanta Sofia', 'Mercadona', 'Lidia', 'Spain', '601443215', NULL, 8),
	(7, 'Suarez', 'Murillo', 'Albacete', '26066', NULL, 'Avenida Niza 54', 'Rotacort', 'Mario', 'Spain', '678009856', NULL, 9);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.empleado: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`id_empleado`, `apellido_1`, `apellido_2`, `ciudad`, `cp`, `descripcion`, `direccion`, `nombre`, `pais`, `puesto`, `telefono`, `fk_id_foto`, `fk_id_usuario`) VALUES
	(1, 'De Jaime', 'Alvarez', 'Madrid', '28022', '', 'Calle la presilla 54', 'Oscar', 'España', 'Director', '666777888', NULL, 1),
	(2, 'Pelayo', 'Mellas', 'Madrid', '28022', '', 'Calle dos', 'Salma', 'España', 'Director', '678443212', NULL, 2),
	(3, 'Martos', 'Lopez', 'Madrid', '28033', NULL, 'Calle sofia 176', 'Adrian', 'España', 'Director', '678008989', NULL, 3),
	(4, 'Martinez', 'Gonzalez', 'Barcelona', '43123', 'Me considero una persona eficiente y trabajadora que le fascina que un trabajo este bien hecho', 'Gran via 56', 'Juan', 'España', 'Empleado', '654343412', NULL, 4),
	(6, 'Garcia', 'Gutierrez', 'Albacete', '32022', NULL, 'Calle niza 32', 'Lorena', 'España', 'Empleado', '611111134', NULL, 5),
	(7, 'Vieco', 'Villa', 'Madrid', '28033', NULL, 'Calle suiza', 'Marta', 'España', 'Empleado', '655431212', NULL, 6);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;

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

-- Volcando datos para la tabla proyectoaso.fichero: ~29 rows (aproximadamente)
/*!40000 ALTER TABLE `fichero` DISABLE KEYS */;
INSERT INTO `fichero` (`id_fichero`, `fecha_mod`, `nombre`, `uri`, `fk_id_proyecto`) VALUES
	(1, '2021-05-31 00:00:00', 'requisitos.txt', 'proyectoASOFiles\\ASM_Compiler', 8),
	(3, '2021-05-30 00:00:00', 'codigo.zip', 'proyectoASOFiles\\ASM_Compiler', 8),
	(4, '2021-05-28 00:00:00', 'readme.txt', 'proyectoASOFiles\\Bytecode_Land_Kit', 9),
	(5, '2020-05-31 00:00:00', 'presentacion.pdf', 'proyectoASOFiles\\Bytecode_Land_Kit', 9),
	(6, '2019-05-31 00:00:00', 'funcion.py', 'proyectoASOFiles\\Bytecode_Land_Kit', 9),
	(7, '2021-05-28 00:00:00', 'paginaweb.html', 'proyectoASOFiles\\Givememykbps', 10),
	(8, '2021-05-27 00:00:00', 'readme.txt', 'proyectoASOFiles\\Givememykbps', 10),
	(9, '2021-05-28 00:00:00', 'prototipo.zip', 'proyectoASOFiles\\Givememykbps', 10),
	(10, '2021-05-27 00:00:00', 'style.css', 'proyectoASOFiles\\Givememykbps', 10),
	(11, '2021-05-28 00:00:00', 'modulo.xml', 'proyectoASOFiles\\Givememykbps', 10),
	(12, '2021-05-28 00:00:00', 'script.sql', 'proyectoASOFiles\\Givememykbps', 10),
	(13, '2021-05-29 00:00:00', 'presentacion.pdf', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(14, '2021-05-29 00:00:00', 'view.xml', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(15, '2021-05-29 00:00:00', 'manifest.py', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(16, '2021-05-29 00:00:00', 'security.xml', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(17, '2021-05-27 00:00:00', 'icon.png', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(18, '2021-05-27 00:00:00', 'init.py', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(19, '2021-05-26 00:00:00', 'models.py', 'proyectoASOFiles\\Viewer_for_Android', 11),
	(20, '2021-05-23 00:00:00', 'demo.exe', 'proyectoASOFiles\\JTVP', 12),
	(21, '2021-05-25 00:00:00', 'readme.txt', 'proyectoASOFiles\\JTVP', 12),
	(22, '2021-05-20 00:00:00', 'datos.xslt', 'proyectoASOFiles\\JTVP', 12),
	(23, '2021-05-13 00:00:00', 'anotaciones.docx', 'proyectoASOFiles\\JTVP', 12),
	(24, '2021-03-30 00:00:00', 'bd.sql', 'proyectoASOFiles\\JTVP', 12),
	(25, '2021-05-31 00:00:00', 'documentacion.pdf', 'proyectoASOFiles\\JTVP', 12),
	(26, '2021-02-28 00:00:00', 'mejoras.pdf', 'proyectoASOFiles\\JTVP', 12),
	(27, '2021-04-30 00:00:00', 'objetivos_principales.pdf', 'proyectoASOFiles\\KillerSheeps', 13),
	(28, '2021-02-28 00:00:00', 'mejorar_adicionales.pdf', 'proyectoASOFiles\\KillerSheeps', 14),
	(29, '2021-03-30 00:00:00', 'demo.exe', 'proyectoASOFiles\\KillerSheeps', 14),
	(30, '2021-03-30 00:00:00', 'problemas_encontrados.txt', 'proyectoASOFiles\\KillerSheeps', 14);
/*!40000 ALTER TABLE `fichero` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.foto_user
CREATE TABLE IF NOT EXISTS `foto_user` (
  `id_foto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `uri` varchar(200) NOT NULL,
  PRIMARY KEY (`id_foto`),
  UNIQUE KEY `UKsmq88eya5apnls0ujq8thw6yh` (`uri`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.foto_user: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `foto_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_user` ENABLE KEYS */;

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

-- Volcando datos para la tabla proyectoaso.jornada: ~30 rows (aproximadamente)
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
INSERT INTO `jornada` (`id_jornada`, `fecha`, `hora_fin`, `hora_inicio`, `iniciada`, `fk_id_empleado`) VALUES
	(1, '2021-05-09 10:59:08', '18:58:03', '10:59:08', b'0', 4),
	(2, '2021-05-08 12:03:49', '20:56:47', '12:03:49', b'0', 4),
	(3, '2021-05-10 12:44:30', '22:45:14', '12:44:30', b'0', 4),
	(4, '2021-05-11 18:46:47', '22:47:02', '18:46:47', b'0', 4),
	(5, '2021-05-12 16:48:37', '23:48:36', '16:48:37', b'0', 4),
	(6, '2021-05-13 06:59:05', '14:58:34', '06:59:05', b'0', 4),
	(7, '2021-05-14 07:57:18', '14:58:26', '07:57:16', b'0', 4),
	(8, '2021-05-15 07:00:50', '12:01:50', '07:00:50', b'0', 4),
	(9, '2021-05-16 10:03:31', '18:00:30', '10:03:31', b'0', 4),
	(10, '2021-05-17 12:06:45', '20:00:58', '12:06:45', b'0', 4),
	(11, '2021-05-09 15:00:58', '22:00:34', '15:00:58', b'0', 6),
	(12, '2021-05-10 10:30:27', '17:15:43', '10:30:27', b'0', 6),
	(13, '2021-05-11 11:32:49', '23:33:10', '11:32:49', b'0', 6),
	(14, '2021-05-12 16:30:03', '21:34:27', '16:30:03', b'0', 6),
	(15, '2021-05-13 12:30:12', '18:35:23', '12:30:12', b'0', 6),
	(16, '2021-05-14 09:30:52', '23:37:28', '09:30:52', b'0', 6),
	(17, '2021-05-15 12:45:45', '17:46:05', '12:45:45', b'0', 6),
	(18, '2021-05-16 13:45:53', '18:43:10', '13:45:53', b'0', 6),
	(19, '2021-05-17 09:45:32', '17:46:01', '09:45:39', b'0', 6),
	(20, '2021-05-18 09:47:32', '17:44:43', '09:47:32', b'0', 6),
	(21, '2021-05-09 10:59:08', '18:58:03', '10:59:08', b'0', 7),
	(22, '2021-05-08 12:03:49', '20:56:47', '12:03:49', b'0', 7),
	(23, '2021-05-10 12:44:30', '22:45:14', '12:44:30', b'0', 7),
	(24, '2021-05-11 18:46:47', '22:47:02', '18:46:47', b'0', 7),
	(25, '2021-05-12 16:48:37', '23:48:36', '16:48:37', b'0', 7),
	(26, '2021-05-09 15:00:58', '22:00:34', '15:00:58', b'0', 7),
	(27, '2021-05-11 11:32:49', '23:33:10', '11:32:49', b'0', 7),
	(28, '2021-05-12 16:30:03', '21:34:27', '16:30:03', b'0', 7),
	(29, '2021-05-13 12:30:12', '18:35:23', '12:30:12', b'0', 7),
	(30, '2021-05-16 13:45:53', '18:43:10', '13:45:53', b'0', 7);
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.log_cambio_jornada
CREATE TABLE IF NOT EXISTS `log_cambio_jornada` (
  `id_log_cambio_jornada` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Tipo_mensaje` enum('Aviso','Cambiado') NOT NULL,
  `Empleado` int(11) unsigned NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_log_cambio_jornada`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyectoaso.log_cambio_jornada: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `log_cambio_jornada` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_cambio_jornada` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.log_cliente_dia
CREATE TABLE IF NOT EXISTS `log_cliente_dia` (
  `id_log_cliente_dia` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cantidad_clientes` int(11) unsigned NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_log_cliente_dia`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.log_cliente_dia: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `log_cliente_dia` DISABLE KEYS */;
INSERT INTO `log_cliente_dia` (`id_log_cliente_dia`, `cantidad_clientes`, `fecha`) VALUES
	(3, 5, '2021-06-07 00:00:00'),
	(4, 3, '2021-06-08 00:00:00'),
	(5, 8, '2021-06-09 00:00:00'),
	(6, 9, '2021-06-10 00:00:00'),
	(7, 4, '2021-06-11 00:00:00'),
	(8, 2, '2021-06-12 00:00:00'),
	(9, 3, '2021-06-13 00:00:00'),
	(10, 0, '2021-06-14 00:00:00'),
	(11, 1, '2021-06-15 00:00:00'),
	(12, 10, '2021-06-16 00:00:00'),
	(13, 2, '2021-06-17 00:00:00'),
	(14, 7, '2021-06-18 00:00:00'),
	(15, 8, '2021-06-19 00:00:00'),
	(16, 10, '2021-06-20 00:00:00');
/*!40000 ALTER TABLE `log_cliente_dia` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.log_creacion_usuario
CREATE TABLE IF NOT EXISTS `log_creacion_usuario` (
  `id_log_creacion_usuario` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `id_usuario` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_log_creacion_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.log_creacion_usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `log_creacion_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_creacion_usuario` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.log_empleado_dia
CREATE TABLE IF NOT EXISTS `log_empleado_dia` (
  `id_log_empleado_dia` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `cantidad_empleados` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_log_empleado_dia`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.log_empleado_dia: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `log_empleado_dia` DISABLE KEYS */;
INSERT INTO `log_empleado_dia` (`id_log_empleado_dia`, `fecha`, `cantidad_empleados`) VALUES
	(2, '2021-06-07 00:00:00', 1),
	(3, '2021-06-08 00:00:00', 4),
	(4, '2021-06-09 00:00:00', 2),
	(5, '2021-06-10 00:00:00', 10),
	(6, '2021-06-11 00:00:00', 3),
	(7, '2021-06-12 00:00:00', 6),
	(8, '2021-06-13 00:00:00', 1),
	(9, '2021-06-14 00:00:00', 7),
	(10, '2021-06-15 00:00:00', 5),
	(11, '2021-06-16 00:00:00', 8),
	(12, '2021-06-17 00:00:00', 4),
	(13, '2021-06-18 00:00:00', 9),
	(14, '2021-06-19 00:00:00', 5),
	(15, '2021-06-20 00:00:00', 7);
/*!40000 ALTER TABLE `log_empleado_dia` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.log_errores
CREATE TABLE IF NOT EXISTS `log_errores` (
  `id_error` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `texto_error` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_error`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyectoaso.log_errores: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `log_errores` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_errores` ENABLE KEYS */;

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

-- Volcando datos para la tabla proyectoaso.nota: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` (`id_nota`, `nombre`, `nota`, `fk_id_usuario`) VALUES
	(2, 'nota1', 'Descarga de la nueva versión del proyecto', 4),
	(3, 'nota2', 'Implementación de la nueva funcionalidad', 5),
	(4, 'nota3', 'Comprobacion de errores del proyecto y preguntar acerca de la nueva funcionalidad que se quiere implementar en el proyecto', 2);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.proyecto
CREATE TABLE IF NOT EXISTS `proyecto` (
  `id_proyecto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `progreso` enum('iniciado','en_curso','finalizado','aceptado') NOT NULL,
  PRIMARY KEY (`id_proyecto`),
  UNIQUE KEY `UKgvu9yy8rcb4h2hr1f6av0f0ml` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.proyecto: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
INSERT INTO `proyecto` (`id_proyecto`, `descripcion`, `nombre`, `progreso`) VALUES
	(8, 'Ampliación de la características de la aplicación ASM Compiler de diseño de circuitos digitales avanzados mediante diagramas, para hacerla más libre y dotarla de capacidad de visualización y edición gráfica.', 'ASM Compiler', 'iniciado'),
	(9, 'Conjunto de herramientas agrupadas en módulos para experimentar con el proceso de compilación', 'Bytecode Land Kit', 'en_curso'),
	(10, 'Aplicacion para la gestion y monitorizacion de la calidad del servicio por procesos', 'Givememykbps', 'en_curso'),
	(11, 'La combinacion del protocolo JPIP y el estándar de compresion JPEG 2000 parece ofrecer la mejor solucion para examinar de forma eficiente imágenes de gran resolución', 'Viewer for Android', 'en_curso'),
	(12, 'Implementación de una máquina virtual multiplataforma para el lenguaje de scripting TJS', 'JTVP', 'finalizado'),
	(13, 'Se trata de un videojuego basado en la mecánica de Angry Birds. Serán unas ovejas que luchan contra lobos y tendrán obstáculos como viento, ingravidez, o inmersas en agua.', 'KillerSheeps', 'finalizado'),
	(14, 'Un framework basado en PHP pensado en los que quieren iniciarse en el lenguaje de un modo cómodo y seguro.', 'LeafWork', 'aceptado');
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;

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

-- Volcando datos para la tabla proyectoaso.proyecto_usuario: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `proyecto_usuario` DISABLE KEYS */;
INSERT INTO `proyecto_usuario` (`id`, `fk_id_proyecto`, `fk_id_usuario`) VALUES
	(1, 8, 1),
	(2, 9, 1),
	(3, 8, 4),
	(4, 9, 4),
	(5, 10, 2),
	(6, 12, 2),
	(7, 10, 5),
	(8, 12, 5),
	(9, 13, 3),
	(10, 14, 3),
	(11, 13, 6),
	(12, 14, 6),
	(13, 11, 3),
	(14, 11, 6);
/*!40000 ALTER TABLE `proyecto_usuario` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(100) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `UKgidd9huji2j14xop37v9dc7li` (`rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.rol: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id_rol`, `rol`) VALUES
	(2, 'CLIENTE'),
	(1, 'DIRECTOR'),
	(3, 'EMPLEADO');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;

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

-- Volcando datos para la tabla proyectoaso.rol_usuario: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `rol_usuario` DISABLE KEYS */;
INSERT INTO `rol_usuario` (`id`, `fk_id_rol`, `fk_id_usuario`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 3, 4),
	(5, 3, 5),
	(6, 3, 6),
	(7, 2, 7),
	(13, 2, 8),
	(14, 2, 9);
/*!40000 ALTER TABLE `rol_usuario` ENABLE KEYS */;

-- Volcando estructura para tabla proyectoaso.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `correo_corporativo` varchar(80) NOT NULL,
  `password_usuario` varchar(100) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla proyectoaso.usuario: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `activo`, `correo_corporativo`, `password_usuario`) VALUES
	(1, b'1', 'oscar@aso.codes', '$2a$10$fWAFdrbfeggXvTYvgUdoleKcRBxINpWQjRQR9fHZB24pSTK6cGEj2'),
	(2, b'1', 'salma@aso.codes', '$2a$10$I0s2uCDnJTEQ6w3jsbGnt.HyNCwfv1hipEucX8/zNvP7Fp6HgHrv6'),
	(3, b'1', 'adrian@aso.codes', '$2a$10$AMvD9z5eZDcX1bWrwmYO.uXyfY6Hxc55JQtazJLKCHKchwq4QakKW'),
	(4, b'1', 'juan@aso.codes', '$2a$10$43EehUx.X.QTInECouRQ.e4dL7Hys.B47nwcesoMJbAKbjTzrkr2O'),
	(5, b'1', 'lorena@aso.codes', '$2a$10$Q3SMnCvytULOWLcGI20fpuUViK.ADuII0CfCBNyiFCS2w2TqiWTN6'),
	(6, b'1', 'marta@aso.codes', '$2a$10$5.O2jsR77adVyRLN2rMcXuIkXWzdaLVlweDK3Q68IFtQDnAIFtkgq'),
	(7, b'1', 'jose@aso.codes', '$2a$10$B1l/xHr.mb87OMng7lqk3ObQaNbCNcGdAy2gSd4h7gPVL2phMeRem'),
	(8, b'1', 'lidia@aso.codes', '$2a$10$k2QR56bSRiO1l6cd7CG7BOLCRQKR5R3lCs.U80RfnvsxMaDyfE/lq'),
	(9, b'1', 'mario@aso.codes', '$2a$10$Jn0AUOJRslKG.djDT99TjODdOdasZCLqJhjAoGbX4ClgM8kJNSA0i');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para disparador proyectoaso.check_jornada_iniciada
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_jornada_iniciada` BEFORE INSERT ON `jornada` FOR EACH ROW BEGIN

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
			INSERT INTO log_errores (texto_error) VALUES ('Error al abrir una jornada ya que ya se había iniciada una jornada con el mismo usuario');
		
	

	END IF;
	
	ELSEIF VfechaCount>1 then
	
		SELECT COUNT(hora_fin) into VhoraNull FROM jornada WHERE hora_fin= NULL;

        if VhoraNull = 1 then

                INSERT INTO log_errores (texto_error) VALUES ('Error al abrir una jornada ya que ya se había iniciada una jornada con el mismo usuario');

        ELSEIF VhoraNull= 0 then

                SELECT max(hora_fin) INTO VhoraMax FROM jornada;
                SELECT iniciada INTO ViniciadaMax FROM jornada WHERE hora_fin = VhoraMax AND fecha =VfechaMax;

                IF ViniciadaMax= 1 then

                    INSERT INTO log_errores (texto_error) VALUES ('Error al abrir una jornada ya que ya se había iniciada una jornada con el mismo usuario');

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
