Algoritmo MenúSopaDeLetrasPSeInt
Definir forma, opcion2, opcion3, numpalabras, i Como Entero
    Definir palabra, nombre, palabraAntigua, palabraNueva, palabraEliminar Como Cadena
    Definir palabras Como Cadena
    Dimensionar palabras[100]
    
	
    Repetir
        Escribir " ---Comenzar Partida---"
        Escribir "Ingrese su nombre"
        Leer nombre
    Hasta Que Longitud(nombre) > 0
    
    Mientras (opcion2 <> 3)
        Escribir "Seleccione una opción"
        Escribir " 1. Menú "
        Escribir " 2. Jugar "
        Escribir " 3. Terminar Partida "
		Escribir " 4. Historial de Partidas "
		Escribir " 5. Puntuaciones altas "
		Escribir " 6. Informacion del creador "
        Escribir " Opcion a seleccionar"
        Leer opcion2
        
        Segun opcion2 Hacer
            Caso 1:
                Mientras opcion3 <> 4 Hacer
                    Escribir " 1. Insertar"
                    Escribir " 2. Modificar"
                    Escribir " 3. Eliminar "
                    Escribir " 4. Salir "
                    Escribir " Opcion a seleccionar"
                    Leer opcion3
                    
                    Segun opcion3 Hacer
                        Caso 1:
                            Escribir "Ingrese el número de palabras que desea insertar"
                            Leer numpalabras
                            
                            Para i <- 1 Hasta numpalabras Hacer
                                Repetir
                                    Escribir "Ingrese la palabra (entre 3 y 8 caracteres): "
                                    Leer palabra
                                Hasta Que Longitud(palabra) >= 3 Y Longitud(palabra) <= 8
                                palabras[i] <- palabra
                                Escribir "Palabra registrada: ", palabra
                            FinPara
							
                        Caso 2:
                            Escribir "Modificar una palabra existente"
                            Si numpalabras > 0 Entonces
                                Escribir "Ingrese la palabra que desea modificar:"
                                Leer palabraAntigua
                                
                                i <- 1
                                Mientras i <= numpalabras Y palabras[i] <> palabraAntiguaHacer
                                    i <- i + 1
                                FinMientras
                                
                                Si i <= numpalabras Entonces
                                    Repetir
                                        Escribir "Ingrese la nueva palabra (entre 3 y 8 caracteres): "
                                        Leer palabraNueva
                                    Hasta Que Longitud(palabraNueva) >= 3 Y Longitud(palabraNueva) <= 8
                                    palabras[i] <- palabraNueva
                                    Escribir "Palabra modificada exitosamente."
                                SiNo
                                    Escribir "La palabra no fue encontrada."
                                FinSi
                            SiNo
                                Escribir "No hay palabras para modificar."
                            FinSi
							
                        Caso 3:
                            Escribir "Eliminar una palabra"
                            Si numpalabras > 0 Entonces
                                Escribir "Ingrese la palabra que desea eliminar:"
                                Leer palabraEliminar
                                
                                i <- 1
                                Mientras i <= numpalabras Y palabras[i] <> palabraEliminar Hacer
                                    i <- i + 1
                                FinMientras
                                
                                Si i <= numpalabras Entonces
                                    Para i <- i Hasta numpalabras - 1 Hacer
                                        palabras[i] <- palabras[i + 1]
                                    FinPara
                                    numpalabras <- numpalabras - 1
                                    Escribir "Palabra eliminada exitosamente."
                                SiNo
                                    Escribir "La palabra no fue encontrada."
                                FinSi
                            SiNo
                                Escribir "No hay palabras para eliminar."
                            FinSi
							
                        Caso 4:
                            Escribir "Saliendo del menú..."
                            opcion2 <- 0  
                        De Otro Modo:
                            Escribir "---Opción inválida---"
                    FinSegun
                FinMientras
				
            Caso 2:
                Escribir "Jugar"
            Caso 3:
                Escribir "Terminar Partida"
				opcion2 <- 0 
			Caso 4:
                Escribir "Personas que han Jugado"
				opcion2 <- 0 
			Caso 5:
                Escribir "Top de los 3 mejores jugadores"
				opcion2 <- 0 
			Caso 6:
                Escribir "Kimberly Samantha Gómez Chávez"
				Escribir "202402473"
				Escribir "Sección: B"
				opcion2 <- 0 
            De Otro Modo:
                Escribir "---Opción inválida---"
        FinSegun
    FinMientras
FinAlgoritmo
