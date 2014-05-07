import java.io.Serializable;
import java.util.List;

class AnswerNuevo implements Serializable {
	int server_error;
    sNuevo answer;
}

class AnswerInt implements Serializable {
    int server_error;
    int answer;
}

class AnswerPlantilla implements Serializable {
   int server_error;
    List<Jugador> answer;
}

class AnswerRepertorio implements Serializable {
    int server_error;
    List<Juego> answer;
}

class AnswerLista implements Serializable {
    int server_error;
    sLista answer;
}