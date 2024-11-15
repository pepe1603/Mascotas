-- src/main/resources/data.sql

-- Crear la tabla si no existe
CREATE TABLE IF NOT EXISTS mascota (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    especie VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    descripcion TEXT,
    edad INT,
    fecha_registro DATETIME
);

-- Insertar datos iniciales en la tabla mascota
INSERT INTO mascota (nombre, especie, color, descripcion, edad, fecha_registro)
VALUES
  ('Firulais', 'PERRO', 'MARRON', 'Un perro muy juguetón', 5, '2024-11-13 10:00:00'),
  ('Michi', 'GATO', 'GRIS', 'Un gato tranquilo', 3, '2024-11-13 10:00:00'),
  ('Huesitos', 'CONEJO', 'BLANCO', 'Un conejo veloz', 2, '2024-11-13 10:00:00'),
  ('Guppy', 'PEZ', 'MULTICOLOR', 'Un pez tropical', 1, '2024-11-13 10:00:00'),
  ('Pelusa', 'HURON', 'NEGRO', 'Un hurón muy curioso', 4, '2024-11-13 10:00:00'),
  ('Capitan', 'PERRO', 'GRIS', 'Un capitan listo y activo para nuevas aventuras', 4, '2024-11-13 10:00:00');
