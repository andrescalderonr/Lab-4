## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

Estas son las dependencias que se usaron:

![](img/dependencias.png)

Se utilizaron las siguientes anotaciones para garantizar el diseño
* Clase de servicio
![](img/anotaciones_servicio.png)
* Clase de persistencia
![](img/anotaciones_persistencia.png)

Tambien se creo una clase de config para la inyeccion 
![](img/appConfig.png)

2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

Implementación de getBluePrint y getBlueprintsByAuthor:

![](img/implement.png)

Tests de los 2 metodos:

![](img/testGetBluePrint.png)

![](img/testGetBluePrintsByAuthor.png)


3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

Para esto se implemento una clase appMain en la cual se prueba lo solicitado

![](img/appMain.png)

y nos responde con:

![](img/salida_main.png)

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

Primero creamos la interfaz de los filtros

![](img/filtros.png)

Para despues hacer la implementacion de estos mismos
* Redundantes
![](img/filtro_redundante.png)
* Submuestreo
![](img/filtro_submuestreo.png)

ahora se modifica el servicio para que use filtro
![](img/servcioModificado.png)

y se le agrega a un filtro la anotacion de @Component para que sea reconocido por el appConfig

Se modifico los blueprint del main para mostrar los efectos de los filtros

![](img/mainDeFiltros.png)

Esta es su salida con filtros
* Redundancia
![](img/salidaRedundante.png)
* Submuestreo
![](img/salidaSubmuestreo.png)

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 

Las clases de pruebas se crearon y estan junto a la clase de pruebas que venia
![](img/img.png)
![](img/img_1.png)

y para la anotacion, solo es cambiar de lugar entre las clases la anotacion de @component y la que la tenga activa sera la utilizada, actualmente se dejo activa SubsamplingFilter y comentada en RedundancyFilter



