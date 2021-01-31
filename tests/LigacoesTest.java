package tests;

import src.Simulator.JsonHandler;
import src.Simulator.Ligacoes;
import src.Simulator.Room;

import static org.junit.jupiter.api.Assertions.*;

class LigacoesTest  {

    Room room1 = new Room("room1");
    Room room2 = new Room("room2");
    Room room3 = new Room("room3");
    Ligacoes ligacao = new Ligacoes(room1, room2);

    @org.junit.jupiter.api.Test
    void testGetFrom() {
        String expected = "room1";
        String receved = ligacao.getFrom().getName();
        assertEquals(expected, receved);
    }

    @org.junit.jupiter.api.Test
    void testSetFrom() {
        String expected = "room3";
        ligacao.setFrom(room3);
        String receved = ligacao.getFrom().getName();
        assertEquals(expected, receved);
    }

    @org.junit.jupiter.api.Test
    void testGetTo() {
        String expected = "room2";
        String receved = ligacao.getTo().getName();
        assertEquals(expected, receved);
    }

    @org.junit.jupiter.api.Test
    void testSetTo() {
        String expected = "room3";
        ligacao.setTo(room3);
        String receved = ligacao.getTo().getName();
        assertEquals(expected, receved);
    }
}