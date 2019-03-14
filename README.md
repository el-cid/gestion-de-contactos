# Gestión de contactos

Este gestor de contactos permite importar los contactos de un celular, a partir de un archivo VCF versión 2.1, con el fin de respaldarlos en una base de datos y facilitar su manipulación mediante una computadora de escritorio. 

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

aaaa
