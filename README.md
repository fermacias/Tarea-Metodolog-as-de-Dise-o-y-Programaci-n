# Tarea Alpaca Emblem

## Apectos generales

_El presente código corresponde a la Implementación de un juego llamado Alpala Emblem. Este juego tiene como actores unidades que interactúan entre si pormedio de sus armas, llamadas items. Existen 7 tipos de unidades, estas son archer, fighter, sword master, alpaca, cleric, hero y sorcerer, y existen 7 tipos de items, estos son bow, axe, sword, staff, spear, book y null item.

_Para cumplir con las funcionalidades propias del juego se presentan las clases Tactician y Game Controlles. Un Tactician representa a un jugador de la partida, y el Game Controller es el encargado de comunicar el modelo de juego con el usuario.

## Unidades
_Los métodos generales para las unidades están especificados en la interfaz IUnit, esta se implementa en una clase abstracta AbstractUnit que extiende a cada una de las unidades.

_Cada unidad tiene limitaciones para equiparse items. Estas limitaciones fueron especificadas mediante la implementación de Double Dispatch. Además, una unidad puede entregar un item a otra, pero sin exceder la cantidad máxima de items en el iventario (esta es 3 para todos menos para la alpaca que puede tener ilimitados items).

_Si una unidad tiene un item distinto a nulo equipado y más de 0 hit points puede iniciar un combate con otra. En un combate el item equipado en uno ataca al equipado en el otro y luego se realiza el contra ataque. Alpaca y cleric no pueden iniciar combates, pues no pueden atacar, pero otras unidades si pueden iniciar combates contra ellos.

## Items
_Los métodos generales para las unidades están especificados en la interfaz IEquipableItem, esta se implementa en la clase abstracta AbstractItem que extiende a cada uno de los items.

_Un item puede atacar a otro si es que sus owner se encuentran dentro del rango del item. El daño de un ataque dependera si el item es fuerte, débil o neutro ante el otro, esto se implementó mediante Double Dispatch.

## Tactician 
_Como represenante de un jugador, conoce y maneja todas sus unidades y sus respectivos items, para lo cuál posee métodos que le permiten acceder a todas sus funcionalidades. En particular, tiene la potestad de asignar items a sus unidades, esto lo hace mediante el patrón de diseño Factory.

## Controller
_Es el encargado de llevar el juego. Tiene dos modalidades para inciar una partida, con o sin límites de turno. Para iniciar la partida tiene la facultad de crear a los jugadores, iniciar y asignar unidades, y crear un mapa aleatoreo donde se desarrolla la partida. El juego funciona mediante un sistema de rondas, que se bada en un sistema de turnos aleatoreos. A la hora de obtener los ganadores, si se alcanzó la máxima cantidad de rondas ganan aquel (aquellos) que quedan con la mayor cantidad de unidades, en el caso contrario gana quien sea el único sobreviviente.

_La implementación de esto se sostiene en la implementación de distintos patrones de diseño. Para la asignación de unidades se implementa el patrón factory, además se implementa el patrón observer para realizar los cambios importantes de la partida, esto son: cuando pierde un tactician, cuando muere una unidad y cuando acaba un combate.  



