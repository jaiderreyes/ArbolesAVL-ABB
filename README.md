# Árboles AVL y ABB

Proyecto educativo en Java 17 y Maven para comparar un **árbol binario de
búsqueda (ABB)** con un **árbol AVL autoequilibrado**. Es compatible con Apache
NetBeans e incluye pruebas con JUnit 5.

## Contenido

- Inserción, búsqueda y eliminación en un ABB.
- Recorridos inorden, preorden y postorden.
- Validación de la propiedad del ABB.
- Inserción y eliminación autoequilibradas en un AVL.
- Cálculo de altura y factor de balance.
- Rotaciones LL, RR, LR y RL.
- Pruebas automáticas para las cuatro rotaciones.

## Estructura

```text
src/main/java/com/arboles/
├── app/ArbolesApplication.java
└── tree/
    ├── BinarySearchTree.java
    └── AvlTree.java

src/test/java/com/arboles/tree/
├── BinarySearchTreeTest.java
└── AvlTreeTest.java
```

## Árbol binario de búsqueda — ABB

En un ABB, las claves menores se almacenan a la izquierda y las mayores a la
derecha. El método `insert` conserva esa propiedad:

```java
if (key < node.key) {
    node.left = insert(node.left, key);
} else if (key > node.key) {
    node.right = insert(node.right, key);
}
```

Para eliminar un nodo se contemplan tres situaciones:

1. Sin hijos: se elimina directamente.
2. Con un hijo: el hijo ocupa su lugar.
3. Con dos hijos: se usa el sucesor inorden, el menor nodo del subárbol derecho.

Un ABB puede degradarse hasta parecer una lista enlazada si los valores llegan
ordenados. En ese caso sus operaciones pueden costar `O(n)`.

## Árbol AVL

El AVL añade a cada nodo su altura y mantiene este factor de balance:

```text
factor = altura(subárbol izquierdo) - altura(subárbol derecho)
```

El árbol está balanceado cuando el factor de todos los nodos pertenece a
`{-1, 0, 1}`. Después de insertar o eliminar, `rebalance` aplica la rotación
necesaria.

## Métodos de balanceo

### Caso LL — izquierda-izquierda

La sobrecarga aparece en el hijo izquierdo de la rama izquierda. Se corrige con
una rotación simple a la derecha.

```text
      30             20
     /              /  \
    20      →      10  30
   /
  10
```

### Caso RR — derecha-derecha

La sobrecarga aparece en el hijo derecho de la rama derecha. Se corrige con una
rotación simple a la izquierda.

```text
  10                  20
    \                /  \
     20      →      10  30
       \
        30
```

### Caso LR — izquierda-derecha

Primero se rota el hijo izquierdo hacia la izquierda y después el nodo
desbalanceado hacia la derecha.

```text
      30             20
     /              /  \
    10      →      10  30
      \
       20
```

### Caso RL — derecha-izquierda

Primero se rota el hijo derecho hacia la derecha y después el nodo desbalanceado
hacia la izquierda.

```text
  10                  20
    \                /  \
     30      →      10  30
    /
   20
```

## Complejidad

| Operación | ABB promedio | ABB peor caso | AVL |
|---|---:|---:|---:|
| Buscar | `O(log n)` | `O(n)` | `O(log n)` |
| Insertar | `O(log n)` | `O(n)` | `O(log n)` |
| Eliminar | `O(log n)` | `O(n)` | `O(log n)` |

## Ejecutar en NetBeans

1. Seleccionar **File > Open Project**.
2. Abrir la carpeta `ArbolesAVL-ABB`.
3. Ejecutar `ArbolesApplication.java`.

## Ejecutar con Maven

```bash
mvn test
mvn exec:java
```

## Salida de ejemplo

```text
ABB en orden: [10, 20, 25, 30, 40, 45, 50]
ABB preorden: [30, 20, 10, 25, 40, 50, 45]
Altura del ABB: 4

AVL en orden: [10, 20, 25, 30, 40, 45, 50]
AVL preorden: [30, 20, 10, 25, 45, 40, 50]
Raíz del AVL: 30
Altura del AVL: 3
¿AVL balanceado?: true
```
