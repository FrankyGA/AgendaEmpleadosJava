CREATE DATABASE agenda_empleados;
USE agenda_empleados;

CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    email VARCHAR(100),
    telefono VARCHAR(15),
    direccion VARCHAR(150),
    ciudad VARCHAR(50),
    provincia VARCHAR(50),
    puesto VARCHAR(50),
    departamento VARCHAR(50)
);

-- Crear un usuario "userAgendaEmpleados" con clave "userAgendaEmpleados"
CREATE USER 'userAgendaEmpleados'@'localhost' IDENTIFIED BY 'userAgendaEmpleados';
-- Dar permisos de DATOS sobre la BD agenda
GRANT SELECT, UPDATE, DELETE, INSERT ON agenda_empleados.* TO 'userAgendaEmpleados'@'localhost';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'userAgendaEmpleados'@'localhost';

-- Datos de empleados ficticios
INSERT INTO empleados (nombre, apellidos, fecha_nacimiento, email, telefono, direccion, ciudad, provincia, puesto, departamento) VALUES
('Juan', 'Pérez García', '1985-02-14', 'juan.perez@example.com', '600123456', 'C/ Sierpes, 10', 'Sevilla', 'Sevilla', 'Ingeniero', 'Ingeniería'),
('María', 'López Fernández', '1990-07-23', 'maria.lopez@example.com', '600234567', 'C/ Alfalfa, 22', 'Sevilla', 'Sevilla', 'Administrativa', 'Administración'),
('Antonio', 'González Martínez', '1982-11-05', 'antonio.gonzalez@example.com', '600345678', 'C/ Feria, 5', 'Sevilla', 'Sevilla', 'Técnico', 'Soporte Técnico'),
('Laura', 'Sánchez Rodríguez', '1993-03-18', 'laura.sanchez@example.com', '600456789', 'C/ San Luis, 45', 'Sevilla', 'Sevilla', 'Diseñadora', 'Diseño'),
('Manuel', 'Romero Ruiz', '1978-06-30', 'manuel.romero@example.com', '600567890', 'C/ Triana, 11', 'Sevilla', 'Sevilla', 'Contable', 'Contabilidad'),
('Cristina', 'Jiménez Morales', '1986-12-12', 'cristina.jimenez@example.com', '600678901', 'C/ Betis, 23', 'Sevilla', 'Sevilla', 'Recursos Humanos', 'Recursos Humanos'),
('Javier', 'Torres López', '1991-08-25', 'javier.torres@example.com', '600789012', 'C/ Resolana, 3', 'Sevilla', 'Sevilla', 'Programador', 'IT'),
('Sara', 'Díaz Sánchez', '1989-01-10', 'sara.diaz@example.com', '600890123', 'C/ San Jacinto, 20', 'Sevilla', 'Sevilla', 'Marketing', 'Marketing'),
('Francisco', 'Ortiz Romero', '1984-09-14', 'francisco.ortiz@example.com', '600901234', 'C/ Asunción, 15', 'Sevilla', 'Sevilla', 'Analista', 'Análisis de Datos'),
('Beatriz', 'Muñoz Herrera', '1987-04-22', 'beatriz.munoz@example.com', '600012345', 'C/ Arjona, 8', 'Sevilla', 'Sevilla', 'Product Manager', 'Gestión de Producto'),
('Rafael', 'Vargas Ramos', '1985-10-05', 'rafael.vargas@example.com', '600098765', 'C/ Reyes Católicos, 33', 'Sevilla', 'Sevilla', 'Ingeniero', 'Ingeniería'),
('Ana', 'García Pérez', '1992-02-27', 'ana.garcia@example.com', '600087654', 'C/ Mármoles, 4', 'Sevilla', 'Sevilla', 'Diseñadora Gráfica', 'Diseño'),
('Luis', 'Gómez Martín', '1983-06-15', 'luis.gomez@example.com', '600076543', 'C/ Virgen de Luján, 40', 'Sevilla', 'Sevilla', 'Administrador de Redes', 'IT'),
('Marta', 'Rojas Gil', '1989-07-30', 'marta.rojas@example.com', '600065432', 'C/ Amor de Dios, 9', 'Sevilla', 'Sevilla', 'Reclutadora', 'Recursos Humanos'),
('Sergio', 'Fernández Ruiz', '1990-03-20', 'sergio.fernandez@example.com', '600054321', 'C/ Imagen, 17', 'Sevilla', 'Sevilla', 'Contable', 'Contabilidad'),
('Elena', 'Navarro Díaz', '1988-11-11', 'elena.navarro@example.com', '600043210', 'C/ San Vicente, 12', 'Sevilla', 'Sevilla', 'Analista de Sistemas', 'Análisis de Datos'),
('Pablo', 'Molina Suárez', '1981-05-28', 'pablo.molina@example.com', '600032109', 'C/ Adriano, 6', 'Sevilla', 'Sevilla', 'Soporte Técnico', 'Soporte Técnico'),
('Raquel', 'Lara Muñoz', '1994-08-14', 'raquel.lara@example.com', '600021098', 'C/ Alfonso XII, 25', 'Sevilla', 'Sevilla', 'Consultora', 'Consultoría'),
('Victor', 'Medina Cortés', '1990-12-01', 'victor.medina@example.com', '600010987', 'C/ Zaragoza, 1', 'Sevilla', 'Sevilla', 'Desarrollador Web', 'IT'),
('Alicia', 'Serrano Campos', '1987-09-16', 'alicia.serrano@example.com', '600009876', 'C/ Real, 18', 'Sevilla', 'Sevilla', 'Especialista en SEO', 'Marketing');

INSERT INTO empleados (nombre, apellidos, fecha_nacimiento, email, telefono, direccion, ciudad, provincia, puesto, departamento) VALUES
('Isabel', 'Hernández Castro', '1984-01-15', 'isabel.hernandez@example.com', '600123890', 'C/ San Fernando, 19', 'Sevilla', 'Sevilla', 'Diseñadora UX', 'Diseño'),
('Fernando', 'García Romero', '1991-11-22', 'fernando.garcia@example.com', '600234901', 'C/ San Juan, 45', 'Sevilla', 'Sevilla', 'Ingeniero de Software', 'IT'),
('Verónica', 'Soto Pérez', '1983-03-12', 'veronica.soto@example.com', '600345012', 'C/ Conde de Barajas, 33', 'Sevilla', 'Sevilla', 'Gerente de Proyectos', 'Administración'),
('Raúl', 'Alonso Ruiz', '1990-06-25', 'raul.alonso@example.com', '600456123', 'C/ ODonnell, 21', 'Sevilla', 'Sevilla', 'Asistente Administrativo', 'Administración'),
('Clara', 'Vidal Sánchez', '1985-07-18', 'clara.vidal@example.com', '600567234', 'C/ José de Gálvez, 7', 'Sevilla', 'Sevilla', 'Investigadora', 'Investigación y Desarrollo'),
('Álvaro', 'Peña Gómez', '1992-02-28', 'alvaro.pena@example.com', '600678345', 'C/ Puerta de Carmona, 8', 'Sevilla', 'Sevilla', 'Agente de Ventas', 'Ventas'),
('Marina', 'Bermúdez López', '1988-04-10', 'marina.bermudez@example.com', '600789456', 'C/ Alameda, 10', 'Sevilla', 'Sevilla', 'Consultora de Marketing', 'Marketing'),
('David', 'Cabrera López', '1990-09-29', 'david.cabrera@example.com', '600890567', 'C/ Ciudad de Sevilla, 3', 'Sevilla', 'Sevilla', 'Técnico de Soporte', 'Soporte Técnico'),
('Patricia', 'Díaz López', '1982-05-16', 'patricia.diaz@example.com', '600901678', 'C/ Martínez de León, 22', 'Sevilla', 'Sevilla', 'Auditor', 'Contabilidad'),
('José', 'González Soler', '1987-10-11', 'jose.gonzalez@example.com', '600012789', 'C/ Cardenal Spinola, 4', 'Sevilla', 'Sevilla', 'Analista Financiero', 'Finanzas');
