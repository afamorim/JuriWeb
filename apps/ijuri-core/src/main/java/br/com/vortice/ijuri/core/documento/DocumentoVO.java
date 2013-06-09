package br.com.vortice.ijuri.core.documento;

import java.net.URLDecoder;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;


public class DocumentoVO extends ValueObject {

    private Long codigo; 
    private String nome;
    private byte[] bytes;
    private DiretorioVO diretorio;
    
    public static int BUFFER_SIZE = 8192; 
    
    public DiretorioVO getDiretorio() {
        return diretorio;
    }
    public void setDiretorio(DiretorioVO diretorio) {
        this.diretorio = diretorio;
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    public String getBase64(){
        String textoCodificado = Base64Encoder.encode(this.bytes);
        
        return textoCodificado;
            
    }
    
    public void setBase64(String texto){
        try{
            this.bytes = Base64Decoder.decodeToBytes(URLDecoder.decode(texto,"UTF-8"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 } 






