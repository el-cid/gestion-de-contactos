# Gestión de contactos

Este gestor de contactos permite importar los contactos de un celular, a partir de un archivo VCF versión 2.1, con el fin de respaldarlos en una base de datos y facilitar su manipulación mediante una computadora de escritorio. El sistema soporta múltiples usuarios.

El gestor soporta la mayor parte de los datos que pueden ser almacenados en un archivo VCF 2.1, de acuerdo al estándar 
<a href="https://tools.ietf.org/html/rfc2425">RFC 2425</a>
, como por ejemplo: multiples direcciones, emails, números de teléfono, la fecha de nacimiento, nombres y apodos. A continuación se muestra un ejemplo de un contacto en formato VCF 2.1 (los datos del contacto son ficticios):

![Sample contact](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/sample_contact.png)

## Desarrollo

Para poder importar los contactos de un archivo VCF a el gestor, se desarrolló un _parser_, utilizando <a href="https://javacc.org/">JavaCC</a> (Java Compiler Compiler) para transformar las expresiones regulares y gramáticas independientes del contexto necesarias para abordar el RFC 2425. A continuación se muestra un diagrama de dependencias de las clases que conforman el _parser_:

![Parser package](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20del%20paquete%20parser.PNG)

La clase más importante del parser es SyntaxChecker, ya que es la que lleva a cabo el proceso principal del análisis sintáctico:

![SyntaxChecker class](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/clase%20SyntaxChecker.PNG)

El desarrollo del gestor se realizó diviendo la funcionalidad del sistema en capas, estas capas están representadas por paquetes compuestos por varias clases. 

![Package diagram](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20de%20paquetes.png)

La estructura general de las clases que conforman el gestor se pueden observar en el siguiente diagrama de clases de alto nivel:

![Complete class diagram](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20clases%20completo.png)

Para lograr apreciar con mayor detalle la estructura de las clases que componen las capas de software del gestor, se muestran los diagramas de clases de las capas de control, datos, modelo y presentación:

<p align="center">
  <b>Capa de modelo:</b><br>
  <img src="https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama_clases_modelo.png">
</p>

<p align="center">
  <b>Capa de control y datos:</b><br>
  <img src="https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20clases%20controlYdata.png">
</p>

<p align="center">
  <b>Capa de presentación:</b><br>
  <img src="https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20clases%20view.png">
</p>

Se desarrollaron algunos diagramas de secuencia durante el desarrollo del gestor de contactos. En estos diagramas de secuencia se muestra el flujo de algunas actividades que puede realizar el usuario con el sistema, por ejemplo: iniciar sesión y exportar contactos:

<p align="center">
  <b>Iniciar sesión:</b><br>
  <img src="https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20secuencia%20iniciarSesion.png">
</p>

<p align="center">
  <b>Exportar contactos:</b><br>
  <img src="https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/diagrama%20secuencia%20exportarContactos.png">
</p>

## Funcionamiento

Finalmente se muestran algunas imagenes del gestor de contactos en funcionamiento:

### Acceso al sistema

Esta es la primera pantalla del programa, en ella, se realiza la autentificación de las credenciales de un usuario o la creación de un nuevo usuario.

### Pantalla principal

En esta sección se puede seleccionar una de las funciones principales del gestor: importar contactos, gestionar contactos o exportar contactos.

![Main](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/Screenshot%20from%202019-03-14%2017-25-58.png)

### Importar contactos

En esta sección se puede arrastrar un archivo VCF y su contenido es procesado y añadido a la base de datos del gestor. 

![Import contacts](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/importar_1.PNG)

### Gestionar contactos

Esta sección permite observar y modificar los contactos de un usuario, así como añadir nuevos contactos directamente desde el gestor.

![Manage contacts](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/gestionar.PNG)

![Manage contacts 2](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/modificar_contacto.PNG)

### Exportar contactos

Por último, esta funcionalidad permite exportar los contactos de un usuario a un nuevo archivo VCF, que puede ser interpretado por un dispositivo móvil.

![Export contacts](https://github.com/el-cid/gestion-de-contactos/blob/master/screenshots/exportar_contactos.PNG)
