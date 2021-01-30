package src.Simulator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import src.Listl.ArrayUnorderedList;
import src.Listl.LinkedList;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class JsonHandler {

    private String cod;
    private int version;
    private JSONArray Edificio;
    private JSONArray Inimigos;
    private JSONArray Ligacoes;
    private JSONArray EntradasSaidas;
    private JSONObject Alvo;
    private boolean mapLoaded = false;
    private ArrayUnorderedList<Enemy> enemyArray = new ArrayUnorderedList<>();
    private LinkedList<Ligacoes> ligacoesLinkedList = new LinkedList<>();
    private LinkedList<Room> entradasSaida = new LinkedList<>();

    JSONParser parser = new JSONParser();
    String location = "src/map.json";

    public void handleJson() throws IOException, ParseException {


    }

    /**
        Copy all enemys array to a LinkedList
     ADICIONAR ESTRUTURA DE ARRAY AQUI!!
     */
    public ArrayUnorderedList<Enemy> getEnemies(){
        int counter = 0;

        //enemyArrayList.
        Iterator itr = Inimigos.iterator();
        while (itr.hasNext()) {
            JSONObject objTmp = (JSONObject) itr.next();
            String divisao = (String) objTmp.get("divisao");
            String nome = (String) objTmp.get("nome");
            long poder = (long) objTmp.get("poder");
            Enemy enemyTmp = new Enemy(nome, (int) poder, divisao);
            enemyArray.addToRear(enemyTmp);
            counter++;
        }
        return enemyArray;
    }



    public JsonHandler() throws IOException, ParseException {
        //Read complete file
        Object obj = parser.parse(new FileReader(location));
        JSONObject jsonObject = (JSONObject) obj;

        //Read Mission Code
        this.cod = (String) jsonObject.get("cod-missao");
        //System.out.println(cod);

        //Read Mission Version
        this.version = Math.toIntExact((long) jsonObject.get("versao"));
        //System.out.println(version);

        //Read All Mission Rooms
        this.Edificio = (JSONArray) jsonObject.get("edificio");
        //System.out.println(Edificio.get(0));

        //Read all Room Edges
        this.Ligacoes = (JSONArray) jsonObject.get("ligacoes");
        System.out.println(Ligacoes.get(0));

        EntradasSaidas = (JSONArray) jsonObject.get("entradas-saidas");
        //System.out.println(EntradasSaidas.get(0));
        Alvo = (JSONObject) jsonObject.get("alvo");
        //System.out.println(Alvo.get("divisao") +" "+ Alvo.get("tipo"));

        //Read All Mission Enemys
        this.Inimigos = (JSONArray) jsonObject.get("inimigos");

        mapLoaded = true;

    }

    public LinkedList<Room> getEntradasSaida(){

        Iterator inOutItr = EntradasSaidas.iterator();
        while (inOutItr.hasNext()){
            String inOut = (String) inOutItr.next();
            Room tmp = new Room(inOut);
            entradasSaida.add(tmp);
        }
       return  entradasSaida;
    }

    public String getAlvo(){
        String alvo = (String) Alvo.get("divisao");
        return alvo;
    }
    public LinkedList<Room> getRooms(){
        LinkedList<Room> roomList = new LinkedList<>();

        if (enemyArray!= null){

            Iterator roomItr = Edificio.iterator();
            while (roomItr.hasNext()) {

                String roomName = (String) roomItr.next();
                Room tmp = new Room(roomName);

                for (int i = 0; i < enemyArray.size(); i++) {
                    if (enemyArray.getByIndex(i).getDivisao().equals(tmp.getName())) {
                        tmp.setDano(enemyArray.getByIndex(i).getPoder());
                    }
                }
                roomList.add(tmp);
            }
        }
        return roomList;
    }

    /**
     * Retunrs the json mission code
     * @return String cod
     */
    public String getCod(){
        return this.cod;
    }

    /**
     * Returns the json mission version
     * @return
     */
    public int getVersion(){
        return this.version;
    }

    public LinkedList<Ligacoes> getLigacoes(){
        for (int i = 0; i < Ligacoes.size(); i++) {
            JSONArray objTmp = (JSONArray) Ligacoes.get(i);

            Room from = new Room((String) objTmp.get(0));
            Room to = new Room((String) objTmp.get(1));

            Ligacoes ligacoes = new Ligacoes(from, to);

            for (int j = 0; j < enemyArray.size(); j++) {
                if (enemyArray.getByIndex(j).getDivisao().equals(to.getName()) || enemyArray.getByIndex(j).getDivisao().equals(from.getName())) {
                    ligacoes.setDano(enemyArray.getByIndex(j).getPoder());
                }
            }
            ligacoesLinkedList.add(ligacoes);
        }
        return ligacoesLinkedList;
    }
}
