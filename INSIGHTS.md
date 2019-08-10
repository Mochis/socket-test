Pruebas para la detección del cierre de la conexión en un lado del socket:

1. Cerrar la conexión en cliente sin timeout
    El servidor no sabe nada hasta que intenta leer del InputStream
2. Cerrar la conexión en cliente con READ TIMEOUT
    El servidor no sabe nada hasta que intenta leer del InputStream
3. Cerrar la conexión en servidor
    El cliente no sabe nada hasta que intenta leer del InputStream
    
En todos los casos cuando se ha cerrado la conexión y en el otro punto se intenta leer
se lanza un `java.net.SocketException`. Se puede concluir que no se puede saber
cuando un extremo de la conexión se ha cerrado a menos que se intente leer datos.
