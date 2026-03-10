CREATE TABLE perfil (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL
);

CREATE TABLE usuario (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  correo_electronico VARCHAR(100) UNIQUE NOT NULL,
  contrasena VARCHAR(255) NOT NULL,
  id_perfil BIGINT,
  FOREIGN KEY (id_perfil) REFERENCES perfil(id)
);

CREATE TABLE curso (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  categoria VARCHAR(100)
);

CREATE TABLE topico (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(200) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  status ENUM('ABIERTO', 'RESPONDIDO', 'RESUELTO', 'CERRADO', 'ARCHIVADO') DEFAULT 'ABIERTO',
  id_usuario BIGINT,
  id_curso BIGINT,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id),
  FOREIGN KEY (id_curso) REFERENCES curso(id)
);

CREATE TABLE respuesta (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mensaje TEXT NOT NULL,
  solucion BOOLEAN DEFAULT FALSE,
  fecha_creacion DATETIME NOT NULL,
  id_topico BIGINT,
  id_usuario BIGINT,
  FOREIGN KEY (id_topico) REFERENCES topico(id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
