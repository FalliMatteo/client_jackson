package com.script;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ClientThread extends Thread{
    private Socket socket;
    private XmlMapper mapper = new XmlMapper();
    private ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    public String getAllStudents(){
        String string = "";
        for(Classroom c : classrooms){
            for(Student s : c.getStudents()){
                string += s.getName() + " " + s.getSurname() + "\n";
            }
        }
        return string;
    }

    @Override
    public void run(){
        String input;
        boolean loop = true;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do{
                input = in.readLine();
                if(input == null || input.isEmpty()){
                    socket.close();
                    break;
                }else{
                    System.out.println("\n" + input);
                    Classroom c = mapper.readValue(input, Classroom.class);
                    classrooms.add(c);
                    System.out.println(getAllStudents());
                }
            }while(loop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
