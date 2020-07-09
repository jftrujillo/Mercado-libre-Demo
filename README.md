## Mercado libre Demo
Demo del uso de las apis de mercado libre para crear una aplicacion con una vista maestro detalle donde se despliegan una 
lista de productos y la posiblidad de ver un detalle de cada uno de ellos. 

A continuacion se muestra como funciona la app. 


## Stack Tecnologico
 
# Arquitectura
 
Para esta aplicacion se usa un acercamiento basado en clean arquitecture, creando modulos o capas claramente diferenciados 
cada uno con una responsabilidad. Los modulos implementados son:

Presentation: Agrupa las clases relacionadas con las vistas y navegacion de la aplicacion.

Domain: Agrupa useCases y los modelos de dominio, los cuales son usados para la logica interna de la aplicacion y para mostrar informacion 
en pantalla

Data: Agrupa la logica de acceso a las fuentes de informacion, adicionalmente provee la logica de tranformacion entre Modelos Api y Modelos de negocio. 

RemoteData: Agrupa la logica e informacion relacionada con el accesso al servidor remoto. Contiene modelos de api e implementa el cliente de acceso al servidor. 

La visiblidad entre modulos se describe a continuacion:

put image here

Algunos autores consideran que la capa de dominio no es necesaria. Para esta implemetacion, hacemos uso de el para separar por comppleto
la vista de sus fuentes de datos, permitiendo un acercamiento mas sencillo pensado a futuro, cuando las fuentes de informacion lleguen a ser mas variadas (Acercamiento con bases de datos locales, o rebundancia de servidores remotos)


Para mas informacion, recomendamos el siguiente material. 

# Comunicacion entre capas: Acercamiento mediante Corutinas

Para la solucion del problema asyncronico en android tenemos diferentes acercamientos como pueden ser RxJava, Buses de datos o CoRutinas. Para este demo, nos apegamos a las recomendaciones dadas por Google y el equipo de android y hacemos una aproximacion mediante CoRutinas, para informacion en detalle recomendamos los siguientes recursos

* [Coroutines](https://developer.android.com/kotlin/coroutines)

Las CoRutinas nos dan una forma sencilla de llevar a cabo operaciones en diferentes hilos de trabajo, y el framework de android esta haciendo un esfuerzo para que este acercamiento trabaje de una mejor manera con el resto de sus componentes de arquitectura.

# Estrategia capa de presentacion: MVVM Single Activity pattern
Para la capa de presentacion haremos uso de un acercamiento basado en MVVM (Model View ViewModel), el cual, hace principal enfasis en tres aspectos: 
1. El uso del ViewModel: Este componente, introducido en jetpack, nos da una estrategia viable para evitar el problema de aplicaciones monoliticas (GodActivity). Lo interesante de este componente, es que nos permite controlar de mejor manera sus instancias internas y como se comportan con el ciclo de vida de la aplicacion, adicionalmente funciona bastante bien con el componente de LiveData.

* [ViewModel](https://www.youtube.com/watch?v=5qlIPTDE274)

2. Single Activity Pattern (NavigationComponent): El single activity patter, empezo a tomar fuerza a medida que el equipo android le daba mas y mas soporte, siendo el NavigationComponent uno de sus principales herrramientas de implemntacion. Este patron, nos sugiere el uso de una sola actividad con diferentes fragments que navegan entre ellos. Esto nos ayuda a evitar problemas recurrentes, como la navegacion y el ciclo de vida de cada una de las pantallas. Cabe destacar, que personalmente no considero que sea el mejor acercamiento para aplicacion mucho mas grandes, pero si puede ser una estrategia para agrupar de manera correcta reglas de negocio que compartir entre si logica y estan fuertemente relacionadas entre si. Recursos recomendados

* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [SingleActivityPattern](https://www.youtube.com/watch?v=2k8x8V77CrU)

# Vista Reactiva: LiveData
La estrategia para inflar las vistas de manera reactiva es mediante liveData, que implementa una estrategia relacionada con la programacion reactiva, enfocada al framework de android. Se recomienda ver estos recursos.

* [liveDataDocumentation](https://developer.android.com/topic/libraries/architecture/livedata)
* [liveDataDevelopersGroup](https://www.youtube.com/watch?v=OMcDk2_4LSk)

# Inyeccion de dependencias: Hilt
Para este demo, se hace uso del nuevo componente de inyeccion de depedencias conocido como hilt. Este componente, construido sobre Dagger2, nos da una serie de herramientas las cuales facilitan la implementacion del arbol de depencias en aplicaciones multimodulares, y el control de scopes de cada una de las depedencias que usamos. Se recomienda estos recursos para su entendimiento mas a fondo

* [hilt](https://developer.android.com/training/dependency-injection/hilt-android)

# UnitTesting
Para las pruebas unitarias, nos limitamos al testing de los modulos de domain y data, ya que ellos son quienes presentan logica de negocio independiente de factores externos (Presentation presenta el sistema operativo android y remotedata describe la conexion al servidor). En caso de requerir testing de la capa de presentacion, se conoce la existencia de herramientas como espresso o roboelectric para lleva a cabo esta funcion.

* [Junit](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
* [Mockito](https://site.mockito.org/)

Para nuestro stack de pruebas, usamos Junit en cooperacion con mockito.

# Conexion remota. ApiClient
Para obtener la informacion necesaria, hacemos uso de la api de mercado libre:

* [MercadoLibreApi](https://developer.android.com/jetpack/androidx/releases/room)
* [Retrofit2](https://square.github.io/retrofit/)

Para su comunicacion, hacemos uso del stack mas conocido en android y remendado por el android team, el cual es retrofit. Para la deserializacion de datos usaremos gson. 

# Diagrama final de clases

El sistema propuesto se describe mediante el siguiente diagrama. 

![Alt](images/Untitled Diagram.drawio)







