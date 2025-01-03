CREATE TABLE public.arrendadores (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo VARCHAR(100),
    contraseña VARCHAR(100),
    promedio NUMERIC(10,2) DEFAULT 0.0 NOT NULL,  
    canti_calif INTEGER DEFAULT 0 NOT NULL       
);

CREATE TABLE public.arrendatarios (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo VARCHAR(100),
    contraseña VARCHAR(100),
    promedio NUMERIC(10,2) DEFAULT 0.0 NOT NULL,  
    canti_calif INTEGER DEFAULT 0 NOT NULL        
);

CREATE TABLE public.propiedades (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_arrendador INTEGER REFERENCES public.arrendadores(id),
    nombre_propiedad VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    municipio VARCHAR(100) NOT NULL,
    tipo_ingreso VARCHAR(50) NOT NULL, 
    descripcion TEXT NOT NULL,
    cantidad_habitaciones INTEGER NOT NULL,
    cantidad_banos INTEGER NOT NULL,
    permite_mascotas BOOLEAN NOT NULL,
    tiene_piscina BOOLEAN NOT NULL,
    tiene_asador BOOLEAN NOT NULL,
    estado VARCHAR(20) DEFAULT 'activo', 
    url_imagen TEXT, 
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
    
);

CREATE TABLE public.contratos (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_propiedad INTEGER REFERENCES public.propiedades(id),
    id_arrendatario INTEGER REFERENCES public.arrendadores(id),
    precio NUMERIC(10,2),
    fecha_inicio TIMESTAMP NOT NULL,
    fecha_final TIMESTAMP NOT NULL,
    estado INTEGER DEFAULT 0 NOT NULL
);
