const db = connect("mongodb://localhost:27017/gestion_tareas_db");
//Creacion de la collection usuarios
db.createCollection("usuarios", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["username", "email", "contrasena"],
      properties: {
        username: {
          bsonType: "string",
          description: "Debe ser una cadena y es obligatorio.",
        },
        email: {
          bsonType: "string",
          pattern: "^.+@.+..+$",
          description:
            "Debe ser una cadena con formato de correo electrónico y es obligatorio.",
        },
        contrasena: {
          bsonType: "string",
          description:
            "Debe ser una cadena y es obligatorio. (Contraseña hasheada)",
        },
      },
    },
  },
});

//Creacion de la collection tareas
db.createCollection("tareas", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["usuario_id", "titulo", "estado"],
      properties: {
        usuario_id: {
          bsonType: "objectId",
          description: "Debe ser un ObjectId que referencia al usuario.",
        },
        titulo: {
          bsonType: "string",
          description: "Debe ser una cadena y es obligatorio.",
        },
        descripcion: {
          bsonType: "string",
          description: "Debe ser una cadena.",
        },
        estado: {
          bsonType: "string",
          enum: ["pendiente", "completada", "en progreso"],
          description:
            "El estado debe ser 'pendiente', 'completada' o 'en progreso'.",
        },
        fecha_creacion: {
          bsonType: "date",
          description: "Debe ser una fecha.",
        },
        fecha_vencimiento: {
          bsonType: "date",
          description: "Debe ser una fecha.",
        },
        prioridad: {
          bsonType: "string",
          enum: ["alta", "media", "baja"],
          description: "La prioridad debe ser 'alta', 'media' o 'baja'.",
        },
        etiquetas: {
          bsonType: "array",
          items: {
            bsonType: "string",
          },
          description: "Las etiquetas deben ser un arreglo de cadenas.",
        },
      },
    },
  },
});

// Insertar usuarios iniciales
db.usuarios.insertMany([
  {
    username: "juanPerez10",
    email: "juan.perez@example.com",
    contrasena: "$2y$10$nBnPupDWn5hwVyoVb7CwS.eBzaQtpLcKO2f/hWMZWqWp9EXIC.yhK",
  },
  {
    username: "anaGomez12",
    email: "ana.gomez@example.com",
    contrasena: "$2y$10$k/ZWdIF8.aAREdLSBzGljue.2.RkAkNt5WVrs5uefFaYjfIUPcBlu",
  },
]);

// Insertar tareas iniciales
db.tareas.insertMany([
  // Tareas de Juan Pérez
  {
    usuario_id: db.usuarios.findOne({ email: "juan.perez@example.com" })._id,
    titulo: "Comprar leche",
    descripcion: "Comprar leche en el supermercado",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-01T00:00:00Z"),
    prioridad: "alta",
    etiquetas: ["supermercado", "compras"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "juan.perez@example.com" })._id,
    titulo: "Revisar correos",
    descripcion: "Leer y responder correos del trabajo",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-01-30T12:00:00Z"),
    prioridad: "media",
    etiquetas: ["trabajo", "email"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "juan.perez@example.com" })._id,
    titulo: "Hacer ejercicio",
    descripcion: "Entrenar 30 minutos en el gimnasio",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-01-29T18:00:00Z"),
    prioridad: "alta",
    etiquetas: ["salud", "ejercicio"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "juan.perez@example.com" })._id,
    titulo: "Terminar informe",
    descripcion: "Escribir el informe mensual del proyecto",
    estado: "en progreso",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-03T00:00:00Z"),
    prioridad: "alta",
    etiquetas: ["trabajo", "documentos"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "juan.perez@example.com" })._id,
    titulo: "Cita médica",
    descripcion: "Consulta de control con el médico",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-10T09:00:00Z"),
    prioridad: "media",
    etiquetas: ["salud", "cita"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "juan.perez@example.com" })._id,
    titulo: "Llamar a Pedro",
    descripcion: "Conversar sobre el proyecto de la oficina",
    estado: "completada",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-01-28T15:00:00Z"),
    prioridad: "baja",
    etiquetas: ["llamada", "trabajo"],
  },

  // Tareas de Ana Gómez
  {
    usuario_id: db.usuarios.findOne({ email: "ana.gomez@example.com" })._id,
    titulo: "Estudiar para el examen",
    descripcion: "Repasar temas de matemáticas",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-01-31T00:00:00Z"),
    prioridad: "media",
    etiquetas: ["estudios", "examen"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "ana.gomez@example.com" })._id,
    titulo: "Limpiar la casa",
    descripcion: "Hacer limpieza general en la casa",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-02T10:00:00Z"),
    prioridad: "baja",
    etiquetas: ["hogar", "limpieza"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "ana.gomez@example.com" })._id,
    titulo: "Reunión con el equipo",
    descripcion: "Coordinar tareas del proyecto con el equipo de trabajo",
    estado: "en progreso",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-05T14:00:00Z"),
    prioridad: "alta",
    etiquetas: ["trabajo", "reunión"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "ana.gomez@example.com" })._id,
    titulo: "Actualizar CV",
    descripcion: "Revisar y actualizar la información del currículum",
    estado: "pendiente",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-07T00:00:00Z"),
    prioridad: "media",
    etiquetas: ["profesional", "cv"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "ana.gomez@example.com" })._id,
    titulo: "Desarrollar API",
    descripcion: "Implementar endpoints en la API del proyecto",
    estado: "en progreso",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-02-10T00:00:00Z"),
    prioridad: "alta",
    etiquetas: ["programación", "backend"],
  },
  {
    usuario_id: db.usuarios.findOne({ email: "ana.gomez@example.com" })._id,
    titulo: "Ir al banco",
    descripcion: "Hacer un depósito en la cuenta de ahorros",
    estado: "completada",
    fecha_creacion: new Date(),
    fecha_vencimiento: new Date("2025-01-28T12:00:00Z"),
    prioridad: "baja",
    etiquetas: ["banco", "finanzas"],
  },
]);
