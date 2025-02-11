## Generador de historias aleatorias
Aprendiendo Java decidí llevar a cabo este pequeño proyecto. Se trata de un generador que crea historias con datos preestablecidos. Si bien no siempre las historias tendrán sentido (aunque en alguna que otra iteración lo tendrá y será bastante divertida [todo a manos del azar]) lo importante es tener en cuenta la estructura del proyecto y mi solución a las distintas problemáticas que se me presentaron durante el desarrollo.

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

> Unos meses atrás, una hechicera orgulloso...

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

[IMAGEN DE THINGCREATION1]

Se sobrescribe el método toString() para retornar el nombre de la *cosa*. También agregué los métodos set y get adjective, checkGenderCompatibility y otros  **métodos abstractos para obtener los distintos pronombres que deberán ser implementados en las clases hijas de Thing.**

[IMAGEN DE THINGCREATION2 Y 3]

## Adjective class
La clase que representa a los adjetivos, **no** hereda de ninguna otra clase. Tiene como atributos:

 - name (string)
 - irregularPluralForm (string)
 - gender (enum)
 - pluralRule (enum)

Los adjetivos se obtienen desde el archivo **adjectives.json** donde hay una lista con varios de los mismos, donde se especifica su regla para convertirlos en plural y su forma irregular en caso de tenerla:

    [
    	{"name": "brillante", "gender": "NO_GENDER", "pluralRule": "S"},
    	{"name": "divertido", "gender": "MALE", "pluralRule": "S"},
    	{"name": "energética", "gender": "FEMALE", "pluralRule": "S"},
    	{"name": "feliz", "gender": "NO_GENDER", "pluralRule": "IRREGULAR", "irregularPluralForm": "felices"}
    	...
    ]
Estos adjetivos son leídos, ordenados y guardados en listas dentro de la clase **AdjectiveManager** la cual se encarga de proveer de adjetivos a los objetos que lo necesiten con las características pedidas. Por ejemplo, la clase **AdjectiveManager** posee un método llamado getRandomAdjective() que toma como parámetro a un **StoryObject**, este método accederá a su género y retornará un adjetivo adecuado para el objeto, al cual se lo podremos aplicar con el método setAdjective() heredado de **Thing**.
Con esto, por fin una generación errónea como esta:

> Hace cien años, un **princesas bonito**...

ahora será:
> Hace cien años, un **princesas bonita**...

Pero, *bonita* debería ser *bonitas*, ¿verdad? Para solucionar esto, agregué el método **getPlural()** a la clase **Adjective**, el cual **teniendo en cuenta lo especificado en el JSON, convertirá el adjetivo a su versión en plural** sin problemas y **retornándolo como un String**.

> Resumiendo todo de forma más fácil (o difícil): Se lee el **JSON**, se guardan los adjetivos en distintas  listas (según su género) de la clase **AdjectiveManager**, llamamos al método **getRandomAdjective(myStoryObject)** y le pasamos como parámetro el objeto al que le queremos aplicar este adjetivo.El adjetivo que nos retornará **getRandomAdjective()** lo seteamos con **myStoryObject.setAdjective()**. Quedando así un **StoryObject** que puede ser, por ejemplo, computadoras, con el adjetivo **potente**, pero cuando lo necesitemos, utilizamos **myStoryObject.getAdjective().getPlural()** y nos quedará "computadoras **potentes**".

## StoryObject
Heredando de **Thing** tenemos a la clase **StoryObject**, la cual representaría a cualquier objeto "tangible" de la historia, como podría serlo una pelota, un auto, un astronauta, etc. Es en esta clase donde comienza a aparecer el atributo **areMany** con el cual puedo verificar si el objeto en realidad se trata de **varios** objetos.