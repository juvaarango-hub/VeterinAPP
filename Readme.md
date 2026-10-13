***Backend en java desarrollado por***
*Darlan Estiwar Muriel Ramirez*
*Cristian Camilo Cano Tejada*



## Épica BE-1. Gestión de usuarios y tutores

### HU-BE-1.1. Registro de usuarios

*Como* sistema backend de VeterinAPP,  
*quiero* registrar nuevos usuarios con sus datos personales y credenciales,  
*para* permitir que las personas creen una cuenta y puedan identificarse en la plataforma.

*Criterios de aceptación:*

- El backend debe recibir los datos requeridos para registrar una cuenta.
- Debe validar que los campos obligatorios estén completos.
- Debe comprobar que el correo electrónico tenga un formato válido.
- Debe verificar que el correo electrónico no esté registrado previamente.
- La contraseña debe cumplir la política mínima de seguridad definida por el sistema, inicialmente de al menos 8 caracteres.
- La contraseña no debe almacenarse en texto plano; debe guardarse mediante un mecanismo seguro de hash.
- Si el registro es exitoso, el usuario debe persistirse y el backend debe devolver una respuesta de confirmación.
- Si los datos no son válidos o el correo ya existe, el backend debe devolver un error comprensible sin crear el registro.

### HU-BE-1.2. Consulta de usuarios y tutores

*Como* componente autorizado del sistema,  
*quiero* consultar los datos de un usuario o tutor registrado,  
*para* recuperar su información y utilizarla en las operaciones que dependan de él.

*Criterios de aceptación:*

- El backend debe permitir consultar un usuario mediante su identificador.
- Debe permitir consultar o listar usuarios según los permisos definidos.
- Si el usuario solicitado no existe, debe devolver una respuesta que indique que no fue encontrado.
- La información sensible, como la contraseña o su hash, no debe incluirse en las respuestas.
- Las consultas deben respetar las restricciones de acceso según el rol del solicitante.

### HU-BE-1.3. Actualización de usuarios y tutores

*Como* usuario autorizado,  
*quiero* actualizar los datos personales de un usuario o tutor,  
*para* mantener su información actualizada en el sistema.

*Criterios de aceptación:*

- El backend debe identificar al usuario que se desea actualizar.
- Debe validar los campos modificados.
- Si se modifica el correo electrónico, debe comprobar que no esté asociado a otra cuenta.
- No debe sobrescribir los datos con valores inválidos.
- La actualización debe conservar la identidad y las relaciones existentes del usuario.
- Si la operación es exitosa, debe persistir los cambios y devolver una respuesta de confirmación.
- Si el usuario no existe, debe informar que no se pudo realizar la actualización.

### HU-BE-1.4. Desactivación de usuarios y tutores

*Como* administrador autorizado,  
*quiero* desactivar una cuenta de usuario,  
*para* impedir su uso cuando ya no deba permanecer activa sin perder la información histórica asociada.

*Criterios de aceptación:*

- El backend debe permitir cambiar el estado de una cuenta a inactivo.
- La cuenta desactivada no debe poder iniciar sesión.
- Los datos históricos y las relaciones con sus mascotas deben conservarse.
- La operación debe estar restringida a los roles autorizados.
- Si la cuenta no existe o ya está desactivada, el backend debe responder de manera controlada.
- La desactivación no debe equivaler a borrar físicamente el registro.

### HU-BE-1.5. Autenticación de usuarios

*Como* usuario registrado,  
*quiero* autenticarme mediante mi correo electrónico y contraseña,  
*para* acceder a las funcionalidades autorizadas de VeterinAPP.

*Criterios de aceptación:*

- El backend debe recibir y validar las credenciales.
- Debe verificar la contraseña mediante el mecanismo seguro de almacenamiento utilizado.
- Si las credenciales son incorrectas, debe devolver un mensaje genérico que no revele si el correo existe.
- Las cuentas inactivas no deben poder autenticarse.
- Si la autenticación es exitosa, debe establecer una sesión o emitir un token seguro, según la arquitectura elegida.
- La respuesta debe permitir identificar al usuario y sus permisos sin exponer información sensible.

### HU-BE-1.6. Cierre de sesión

*Como* usuario autenticado,  
*quiero* cerrar mi sesión,  
*para* impedir que otras personas utilicen mi acceso sin autorización.

*Criterios de aceptación:*

- El backend debe disponer de un mecanismo para cerrar o invalidar la sesión o el token, según la estrategia de autenticación.
- Cuando el cierre de sesión se complete, las credenciales de sesión invalidadas no deben permitir continuar accediendo a recursos protegidos.
- El backend debe confirmar el resultado de la operación.
- La implementación debe ser coherente con el mecanismo de autenticación elegido.

### HU-BE-1.7. Recuperación de contraseña

*Como* usuario que olvidó su contraseña,  
*quiero* solicitar y completar el restablecimiento de mis credenciales,  
*para* recuperar el acceso a mi cuenta de manera segura.

*Criterios de aceptación:*

- El backend debe recibir una solicitud de recuperación asociada a un correo electrónico.
- La respuesta inicial no debe revelar si el correo está registrado.
- Para una cuenta válida, debe generar un token de recuperación único, seguro y con tiempo de expiración.
- El mecanismo de entrega debe enviar el enlace al correo registrado.
- El token debe poder utilizarse una sola vez y debe invalidarse al expirar.
- La nueva contraseña debe cumplir la política de seguridad y almacenarse mediante hash.
- Una vez completado el proceso, el token debe quedar invalidado.
- El backend debe manejar los errores sin exponer información sensible.

---

## Épica BE-2. Gestión de mascotas

### HU-BE-2.1. Registro de mascotas

*Como* tutor autenticado o usuario con permisos de gestión,  
*quiero* registrar una mascota con sus datos generales y asociarla a un tutor,  
*para* mantener su información organizada y permitir futuras consultas de su historial.

*Criterios de aceptación:*

- El backend debe recibir los datos definidos para la mascota: nombre, especie, raza, sexo y edad, entre otros campos que determine el modelo.
- Debe validar los campos obligatorios y los valores permitidos.
- Debe comprobar que el tutor asociado exista y esté habilitado para la operación.
- Cuando el registro sea realizado por un tutor, la asociación debe corresponder al usuario autenticado y no depender únicamente de un identificador enviado por el cliente.
- Debe generar un identificador único para la mascota.
- Debe persistir la mascota con su relación correcta con el tutor.
- Si los datos son válidos, debe devolver una respuesta de confirmación y los datos permitidos de la mascota registrada.
- Si la validación falla, no debe guardar un registro incompleto o inconsistente.

### HU-BE-2.2. Consulta de mascotas

*Como* tutor autenticado o usuario autorizado,  
*quiero* consultar las mascotas registradas y sus datos generales,  
*para* acceder a la información correspondiente según mis permisos.

*Criterios de aceptación:*

- El backend debe permitir consultar una mascota por su identificador.
- Debe permitir obtener el listado de mascotas asociadas a un tutor.
- El listado debe excluir las mascotas inactivas cuando se soliciten únicamente las activas.
- Los usuarios con permisos administrativos o veterinarios podrán consultar información adicional de acuerdo con las reglas de autorización definidas.
- Un tutor no debe poder consultar mascotas pertenecientes a otro tutor si no tiene permisos para hacerlo.
- Si no existe la mascota solicitada, debe devolver una respuesta de recurso no encontrado.
- Las consultas deben conservar la consistencia entre la mascota y su tutor asociado.

### HU-BE-2.3. Actualización de mascotas

*Como* tutor autorizado o usuario con permisos de gestión,  
*quiero* actualizar los datos generales de una mascota,  
*para* corregir errores y mantener su información vigente.

*Criterios de aceptación:*

- El backend debe identificar la mascota que se desea actualizar.
- Debe comprobar que la mascota exista y que el solicitante tenga permiso para modificarla.
- Debe validar los campos recibidos y rechazar los valores inválidos.
- Debe permitir modificar los campos autorizados sin alterar accidentalmente la relación con el tutor.
- La actualización debe persistirse correctamente.
- Si la mascota no existe o el solicitante no tiene permisos, debe devolver una respuesta adecuada.
- La operación no debe borrar ni alterar la información clínica histórica asociada.

### HU-BE-2.4. Desactivación de mascotas

*Como* tutor autorizado,  
*quiero* desactivar una mascota,  
*para* retirarla de mis listados activos sin perder su información histórica.

*Criterios de aceptación:*

- El backend debe permitir cambiar el estado de la mascota a inactivo.
- La mascota desactivada debe dejar de aparecer en los listados activos del tutor.
- Sus datos y relaciones históricas deben conservarse.
- El registro no debe eliminarse físicamente de la base de datos.
- La operación debe verificar que el solicitante tenga permiso para desactivar la mascota.
- El backend debe responder adecuadamente si la mascota no existe o ya está inactiva.
- El modelo debe permitir una posible reactivación futura si las reglas del negocio la contemplan.

---

## Épica BE-3. Gestión de Doctores / Veterinarios

### HU-BE-3.1. Registro de Doctores

*Como* administrador del sistema o profesional veterinario,  
*quiero* registrar a un doctor con sus datos personales, especialidad y número de tarjeta profesional (idMp),  
*para* darlo de alta en la plataforma y permitirle gestionar la atención de las mascotas.

*Criterios de aceptación:*

- El backend debe recibir la información completa del doctor: identificación, nombre, apellido, correo, teléfono, contraseña, tarjeta profesional (`idMp`) y especialidad.
- Debe validar que los campos obligatorios no estén vacíos.
- Debe comprobar que el correo electrónico tenga un formato válido y sea único en el sistema.
- Debe verificar que la tarjeta profesional (`idMp`) sea un número positivo y no esté asignada a otro doctor.
- La contraseña debe almacenarse de forma segura y cumplir con la política mínima.
- Si el registro es exitoso, el doctor debe guardarse con el estado inicial **Activo (`true`)**.
- Si los datos son inválidos o la identificación/correo/idMp ya existen, se debe devolver un mensaje de error explícito sin guardar la entidad.

### HU-BE-3.2. Consulta de Doctores

*Como* administrador o usuario del sistema,  
*quiero* consultar la información detallada de un doctor o listar todos los doctores registrados,  
*para* verificar sus especialidades, datos de contacto y estado actual en la veterinaria.

*Criterios de aceptación:*

- El backend debe permitir la búsqueda individual de un doctor mediante su identificador (`id`).
- Debe brindar la opción de listar la totalidad de los doctores registrados en el sistema.
- Si el doctor consultado por `id` no existe, debe responder con un mensaje o excepción clara indicando que no fue encontrado.
- Las vistas de consulta deben mostrar nombre completo, especialidad, tarjeta profesional (`idMp`), contacto y estado (`Activo` / `Inactivo`).
- No debe exponerse la contraseña en las respuestas de consulta.

### HU-BE-3.3. Actualización de Doctores

*Como* administrador autorizado,  
*quiero* modificar la información personal y profesional de un doctor existente,  
*para* mantener vigentes sus datos de contacto, especialidad y credenciales.

*Criterios de aceptación:*

- El backend debe localizar el doctor a actualizar a partir de su `id`.
- Debe permitir editar campos como nombre, apellido, correo, teléfono, contraseña, tarjeta profesional (`idMp`) y especialidad.
- Debe ejecutar todas las validaciones de negocio sobre los datos ingresados antes de efectuar el cambio.
- Mantendrá el `id` original y el estado de la entidad sin alteración no deseada.
- Si la actualización es exitosa, debe persistir los cambios y confirmar la transacción.
- Si el doctor con el identificador dado no existe, debe notificar que no fue posible realizar la actualización.

### HU-BE-3.4. Desactivación o Eliminación de Doctores

*Como* administrador del sistema,  
*quiero* eliminar o inactivar la cuenta de un doctor,  
*para* restringir su acceso y operatividad cuando deje de laborar en la clínica.

*Criterios de aceptación:*

- El backend debe requerir confirmación explícita previa a la eliminación o cambio de estado.
- Debe permitir remover al doctor de la colección mediante su identificador (`id`).
- Si se utiliza borrado lógico, el estado del doctor debe actualizarse a `Inactivo` (`state = false`).
- Si la operación se realiza correctamente, debe retornar la confirmación del retiro.
- Si la identificación proporcionada no coincide con ningún doctor registrado, debe informar que la entidad no existe.