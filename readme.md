## Generador de historias aleatorias
Aprendiendo Java decidí llevar a cabo este pequeño proyecto. Se trata de un generador que crea historias con datos preestablecidos, con una interfaz gráfica construida en swing. Si bien no siempre las historias tendrán sentido (aunque en alguna que otra iteración lo tendrá y será bastante divertida [todo a manos del azar]) lo importante es tener en cuenta la estructura del proyecto y mi solución a las distintas problemáticas que se me presentaron durante el desarrollo.

![captura de pantalla de la aplicación](https://lh3.googleusercontent.com/d/1OwdvAc4HEPCSuZ_jNTaXPHrCFGfgswyA)
---
#### De una aplicación sencilla a algo más complejo
En un inicio el programa se basaba en recoger una lista de **Strings** desde un archivo **CSV**. Por ejemplo, imaginemos que tenemos un archivo "times.csv" y dentro el contenido: 

> "Hace mucho tiempo, Hace miles de años, Hace millones de años, Unos meses atrás..."

Y un archivo "charactes.csv" con el siguiente contenido:
> "Un mago, Una hechicera, Un ratón, Un aventurero..."

Por último un archivo con adjetivos como:
> "Grande, orgulloso, poderoso..."

Si leemos estos archivos y, acomodamos sus contenidos en Listas para luego seleccionarlos al azar para así formar una historia, podemos obtener algo como esto:
> "Hace millones de años, un mago poderoso..."

Y eso está genial, todo parece funcionar hasta que nos topamos con algo como esto:

> Unos meses atrás, un hechicera orgulloso...

Acá ya somos capaces de ver la limitación de esta implementación de concatenación aleatoria, y esto no es nada, qué tal si agregamos personajes como "Perros, Aventureros" o cualquier cosa que consideremos "muchas" o "varias", estaríamos obteniendo algo como:

> Hace mucho tiempo, un **aventureros** poderoso...

Fue en este momento donde comprendí que no iba a ser tan fácil como aparentaba. Debía saber si cada personaje era masculino o femenino, si estaba en plural. Lo mismo con los adjetivos y verbos junto con su forma irregular.
Por ejemplo debía hacer que las siguientes generaciones:

> Hace millones de años, un **aventureros** fuerte....
> Hace cien años, un **princesas** hermoso....

Se transformen en:

> Hace millones de años, **unos** aventureros **fuertes**....
>  Hace millones de años, **unas** princesas **hermosas**....

## Thing class
El primer paso fue crear una **clase abstracta** llamada **Thing**. Esta "súper clase" representaría cada elemento de la historia, lugares, personajes, objetos, etc.
*Thing* contiene como atributos los siguientes: 

 - **name** (nombre de la cosa)
 - **gender** (género de la cosa, utilizando un **tipo enumerado**)
 - **adjective** (adjetivo de la cosa, de **tipo Adjective**)

![Creación de la clase Thing](https://lh3.googleusercontent.com/d/1GHNPaB-QFXHwk4qlzAkpSeY6FRgTUqUc)

Se sobrescribe el método toString() para retornar el nombre de la *cosa*. También agregué los métodos set y get adjective, checkGenderCompatibility y otros  **métodos abstractos para obtener los distintos pronombres que deberán ser implementados en las clases hijas de Thing.**

![métodos de la clase Thing](https://lh3.googleusercontent.com/d/1wo_NmVn7U9J4wvMrfgddVFsDK0oy0qV1)
![métodos abstractos de la clase Thing](https://lh3.googleusercontent.com/d/1ngcKyy1sdt6jRbTfETn7bQn7gsPPBFnf)
## Adjective class & AdjectiveManager
La clase que representa a los adjetivos, **no** hereda de ninguna otra clase. Tiene como atributos:

 - name (string)
 - irregularPluralForm (string)
 - gender (enum)
 - pluralRule (enum)

Los adjetivos se obtienen desde el archivo **adjectives.json** donde hay una lista con varios de los mismos, donde se especifica su regla para convertirlos en plural y su forma irregular en caso de tenerla, así como también su género:

    [
    	{"name": "brillante", "gender": "NO_GENDER", "pluralRule": "S"},
    	{"name": "divertido", "gender": "MALE", "pluralRule": "S"},
    	{"name": "energética", "gender": "FEMALE", "pluralRule": "S"},
    	{"name": "feliz", "gender": "NO_GENDER", "pluralRule": "IRREGULAR", "irregularPluralForm": "felices"}
    	...
    ]
Estos adjetivos son leídos, ordenados y guardados en listas dentro de la clase **AdjectiveManager** la cual se encarga de proveer de adjetivos a los objetos que lo necesiten con las características solicitadas. Por ejemplo, la clase **AdjectiveManager** posee un método llamado getRandomAdjective() que toma como parámetro a un **StoryObject**, este método accederá a su género y retornará un adjetivo adecuado para el objeto, al cual se lo podremos aplicar con el método setAdjective() heredado de **Thing**.
Con esto, por fin una generación errónea como esta:

> Hace cien años, un **princesas bonito**...

ahora será:
> Hace cien años, un **princesas bonita**...

Pero, *bonita* debería ser *bonitas*, ¿verdad? Para solucionar esto, agregué el método **getPlural()** a la clase **Adjective**, el cual **teniendo en cuenta lo especificado en el JSON, convertirá el adjetivo a su versión en plural** sin problemas y **retornándolo como un String**.

> Resumiendo todo de forma más fácil (o difícil): Se lee el **JSON**, se guardan los adjetivos en distintas  listas (según su género) de la clase **AdjectiveManager**, llamamos al método **getRandomAdjective(myStoryObject)** y le pasamos como parámetro el objeto al que le queremos aplicar este adjetivo.El adjetivo que nos retornará **getRandomAdjective()** lo seteamos con **myStoryObject.setAdjective()**. Quedando así un **StoryObject** que puede ser, por ejemplo, computadoras, con el adjetivo **potente**, pero cuando lo necesitemos, utilizamos **myStoryObject.getAdjective().getPlural()** y nos quedará "computadoras **potentes**".

## StoryObject
Heredando de **Thing** tenemos a la clase **StoryObject**, la cual representaría a cualquier objeto "tangible" de la historia, como podría serlo una pelota, un auto, un astronauta, etc. Es en esta clase donde comienza a aparecer el atributo **areMany** con el cual puedo verificar si el objeto en realidad se trata de **varios** objetos. Además es obligatorio desarrollar los métodos abstractos de la clase **Thing**, que **retornaran los pronombres correspondientes teniendo en cuenta el género del objeto y el valor de la variable areMany**:

 - **getUnityPronoun()**
	 Que me retornará  **"un / una"** o **"unos / unas"**

 - **getSingularPronoun()**
 Retorna **"el / la"** según género.

 - **getPluralPronoun()**
Devolverá **"los / las"**.

 - **getDistantPronoun()**
Devuelve **"Aquel / Aquella"** o **"Aquellos / Aquellas "** según corresponda.

Por último, sobrescribí **equals()** para que sólo sea comparable con otro storyObject y que además, sean iguales si tienen el mismo nombre.

![La clase StoryObject y sus métodos](https://lh3.googleusercontent.com/d/1nCPl6JNOdrz-UKsqtIX5Netn5A7ocJE8)

Con esto, logramos otro avance a la hora de generar la historia, ya que podremos referirnos al personaje u objeto generado de manera correcta.
Pasando de algo como esto:

> Hace millones de años, **un** princesa bonita...

a esto:

> Hace millones de años, **una** princesa bonita... Pero un día **aquella** princesa...

## Verbs & VerbManager
Otro elemento de suma importancia a la hora de contar un relato, son los **verbos**. La implementación es tan simple como guardar los verbos en un archivo json con sus diferentes variables que pueden necesitarse al momento de una generación:

    [  
      {"infinitivo": "buscar", "simple": "buscó", "simplePlural": "buscaron", "imperfecto": "buscaba"},  
      {"infinitivo": "correr", "simple": "corrió", "simplePlural": "corrieron", "imperfecto": "corría"},  
      {"infinitivo": "asesinar", "simple": "asesinó", "simplePlural": "asesinaron", "imperfecto": "asesinaba"}
      ...
    ]
Y crear una clase **Verb** con los correspondientes **getX()**

![Clase verb y sus getters](https://lh3.googleusercontent.com/d/16aJzftDbVJh7WXPage2b-GYdxgby4h_N)
Por último, creé una clase **VerbManager** que guardará todos los verbos en una lista interna y podremos llamar a su método **getRandomVerb()** para obtener un elemento cuando sea necesario, y luego utilizar los métodos **get()** de cada verbo para obtener el "tipo" que necesitemos.

![enter image description here](https://lh3.googleusercontent.com/d/1hrtLEIzdyisvRs7JNNjcuaB9Mdyohf2J)

## StoryGenerator

Una clase que implementa **Runnable**. Encargada de cargar todos los elementos e inicializar todo lo necesario para la generación de la historia (sólo lo hace si no se hizo previamente). Cuenta con el método **createStory()** el cual, por medio de un String builder construirá la historia concatenando los elementos seleccionados de forma aleatoria.

## Extras
La aplicación permite **exportar** la historia a un archivo .txt en donde el usuario desee mediante una **ventana de selección de ruta**. También cuenta con la opción de copiar la generación al **portapapeles**.
