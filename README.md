# Proyecto Programación de Plataformas Móviles

## Introducción
Hemos escuchado una gran cantidad de veces la frase: “la programación es muy difícil” por parte de
personas que están aprendiendo a programar y no se ven interesadas en el mundo de la
programación, posiblemente debido a que, en un primer momento, piensen que no es una parte
central o realmente importante en sus carreras. No obstante, vivimos en un mundo tecnológico en el
que saber programar se va convirtiendo en una necesidad para realizar trabajos con una mayor
eficiencia.

Según el sitio web el sitio web Madridiario, la programación ha permitido que la tecnología haya
avanzado y se vea como la tenemos en la actualidad, y que gracias a esta se logren desarrollar
inventos que faciliten la vida diaria de las personas.
También es posible encontrarla en todo tipo de industrias, desde el entretenimiento hasta la musical,
con especial importancia en el desarrollo de software. La edición de contenido multimedia,
estadística, análisis y ordenamiento de datos son ejemplos del uso que se le puede dar a la
programación.

Es por estos motivos que hemos decidido crear un programa dirigido a las personas que poseen
dificultades para adentrarse en el mundo de la programación o que no conocen realmente qué tanta
importancia tiene en los tiempos actuales. Creemos que, tener una aplicación para Android — el
sistema operativo más usado según la web ProBlogBooster — podría ser un gran apoyo para el tipo
de personas mencionadas.

Decidimos basar el proyecto en el lenguaje de programación Python debido a que se trata de un
lenguaje muy amigable con personas que están empezando, a la vez que permite realizar tareas más
complejas gracias a la gran cantidad de librerías y funciones que posee. Se busca encontrar una
curva de aprendizaje que vaya incrementando conforme el usuario avance por el tutorial y que no lo
abrume con conceptos muy complicados. También es menester que se utilice tanto la teoría como la
práctica con la finalidad de que el usuario vaya comprendiendo todo lo que está realizando con la 
programación.

## Servicios empleados
### Los servicios utilizados para la realización del proyecto son:
- Firebase. Es una plataforma en la nube de Google con enfoque en el desarrollo de aplicaciones
  web y móvil. Su función principal es hacer más sencilla la creación de aplicaciones, apuntando a
  una mayor velocidad, pero sin dejar de lado la calidad del desarrollo.

  Dentro de este proyecto, la principal función de Firebase es la de almacenar la información de los
  usuarios para que sean capaces de crear una cuenta o iniciar sesión para utilizar la aplicación.
  Para ello, se optó por utilizar la función "Autenticación de Usuarios" debido a las maneras con
  las que los usuarios son capaces de iniciar sesión: pueden hacerlo de la forma tradicional
  — correo y contraseña — o pueden utilizar servicios como Google, Facebook o Twitter para
  hacerlo. Estas opciones brindan más opciones al usuario y vuelven más completa a la aplicación, así
  como más cómoda de utilzar. En conclusión, Firebasees una herramienta con la cual se puede
  ahorrar tiempo y, al mismo tiempo, ofrecer un trabajo de calidad con una gran cantidad de opciones para
  volver más completa a cualquier aplicación.
- OpenAI. Es un laboratorio de investigación que se enfoca en la Inteligencia Artificial. Este laboratorio
  se volvió popular por su modelo IA llamado ChatGPT (de las siglas en inglés Generative Pre-trained Transformer,
  un chatbot de IA desarrollado en 2022 que se especializa
  en el diálogo. Dicho chatbot ha recibido tanta atención gracias a sus respuestas detalladas y articuladas, a
  la vez que fue criticado por su presición fáctica.

  Dentro de las ideas para el desarrollo del proyecto, se sugirió implementar un chat en el cual los usuarios
  serían capaces de hacer preguntas sobre los cursos y les respondería un ser humano. Esta idea fue descartada
  a favor de emplear una IA debido a que no se necesita un equipo de personas que respondan las preguntas, sino
  que una IA lo haría más eficientemente. Es entonces que entra en juego la API de ChatGPT. Esta API se convirtió
  rápidamente en el enfoque principal para la realización de este chat, ya que, al conectar Chat GPT con la
  aplicación, los usuarios pueden hacerle preguntas y obtener respuestas rápidamente, lo que no intervendría
  demasiado en su aprendizaje. Además de que podrían hasta expandirlo al analizar formas distintas de solucionar
  un mismo problema.
- Implementación de páginas web para mostrar los cursos. Se tiene planeado tener una serie de paginas web preparadas para
  poder mostrar los cursos sugeridos sin que tenga que ser programado directamente en la aplicación, lo cual permitirá agregar
  videos y otros recursos característicos de paginas web. Para realizar esto se usará el concepto de WebView, utilizando
  kotlin y java para proyectar la página web. Se implementará compose en vez de xml para el Webview. Este servicio no es
  necesario el uso de APIs
