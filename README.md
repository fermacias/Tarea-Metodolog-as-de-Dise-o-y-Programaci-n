# Tarea 1: Alpaca Emblem

## Apectos generales
El código de esta tarea se basa en la implementación de items y unidades, y sus respectivos test. Existen 7 tipos de unidades, estas son archer, fighter, sword master, alpaca, cleric, hero y sorcerer, y existen 7 tipos de unidades, estas son bow, axe, sword, staff, spear, book y null item.

## Unidades
Los métodos generales para las unidades están especificados en la interfaz IUnit, esta se implementa en una clase abstracta AbstractUnit que extiende a cada una de las unidades.

Cada unidad tiene limitaciones para equiparse items. Estas limitaciones fueron especificadas mediante la implementación de Double Dispatch. Además, una unidad puede entregar un item a otra, pero sin exceder la cantidad máxima de items en el iventario (esta es 3 para todos menos para la alpaca que puede tener ilimitados items).

Si una unidad tiene un item distinto a nulo equipado y más de 0 hit points puede iniciar un combate con otra. En un combate el item equipado en uno ataca al equipado en el otro y luego se realiza el contra ataque. Alpaca y cleric no pueden iniciar combates, pues no pueden atacar, pero otras unidades si pueden iniciar combates contra ellos.

## Items
Los métodos generales para las unidades están especificados en la interfaz IEquipableItem, esta se implementa en la clase abstracta AbstractItem que extiende a cada uno de los items.

Un item puede atacar a otro si es que sus owner se encuentran dentro del rango del item. El daño de un ataque dependera si el item es fuerte, débil o neutro ante el otro, esto se implementó mediante Double Dispatch.

El item nullItem es creado para equipar por defecto a cada unidad, por lo que su ataque no hace nada y resulta neutro ante todos los items. La clase correspondiente al item tipo book es abstracta y extiende a animaBook, luzBook y oscuridadBook, todos los books son fuertes y debiles antes todos los items, pero los books tienen prioridad entre ellos.
