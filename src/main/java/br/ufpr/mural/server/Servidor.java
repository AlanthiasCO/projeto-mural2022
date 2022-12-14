package br.ufpr.mural.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufpr.mural.core.usuario.Usuario;

public class Servidor {
    
    private static final int PORTA = 1234; // atributo de classe

    private InMemoryDatabase database; // atributo do objeto
    
    public void iniciar() throws IOException {

        ServerSocket socket = new ServerSocket(PORTA);
        database = new InMemoryDatabase();

        System.out.println("Servidor iniciado.");
        
        try {
            while (true) {
                atenderCliente(socket.accept());
            }
        } finally {
            socket.close();
        }
    }
    
    private void atenderCliente(final Socket cliente) {        
        // A ideia basica para atender um cliente é
        //   - ler comando
        //   - processar comando  (feito por meio do método tratarComando)
        //   - escrever resposta
        
        new Thread() {

            @Override
            public void run() {
                
                ArrayList<String> listaDeResultado; //Lista de retorno
        
                String command = null;
                
                try {
                    command = readLine(cliente.getInputStream());
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }

                listaDeResultado = tratarComando(command); 
                
                try {
                    for (String line: listaDeResultado){
                        writeLine(cliente.getOutputStream(), line);
                    }
                    
                    cliente.close();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }.start(); 
    }
    
    private static String readLine(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }
    
    private static void writeLine(OutputStream out, String linhas) throws IOException {
        out.write(linhas.getBytes());
        out.write('\n');
    }


    private ArrayList<String> tratarComando(String comando){
        
        ArrayList<String> listaDeResultado = new ArrayList<String>();

        if (comando == null){
            listaDeResultado.add("comando-invalido");
            return listaDeResultado;
        }
        
        String tipoComando = comando.split(" ")[0]; //"criar-usuario joao" --> ["criar-usuario", "joao"]
        
        if (tipoComando.equals("limpar-banco")) {  //limpeza do banco para testes
        	this.database = new InMemoryDatabase();
        	listaDeResultado.add("ok");
        	return listaDeResultado;
        }
        
        if (tipoComando.equals("criar-usuario")){
            //System.out.println(command.split(" ").length);
            if (comando.split(" ").length != 2){
                listaDeResultado.add("comando-invalido");
                return listaDeResultado;
            }
            //else:
            String userName = comando.split(" ")[1];
            
            // VERIFICAR SE USUARIO EXISTE:
            if (database.getUsuario(userName) != null) {
            	listaDeResultado.add("usuario-ja-existe");
                return listaDeResultado;
            }
            
            // LÓGICA DE NEGÓCIO
            Usuario user = new Usuario(userName);
            
           
            
            database.inserirUsuario(user);
            
            // RETORNAR SAÍDA
            listaDeResultado.add("ok");
            

     
            
            
        } else if (tipoComando.equals(Command.CREATE_MURAL.toString())){
            // TODO
        }
        //TODO...
        
        return listaDeResultado;

    }

 
    
}