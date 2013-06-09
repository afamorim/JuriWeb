package br.com.vortice.ijuri.documento.cliente.web.form;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.jenkov.prizetags.tree.impl.Tree;
import com.jenkov.prizetags.tree.impl.TreeNode;
import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;

import br.com.vortice.ijuri.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.documento.DiretorioVO;
import br.com.vortice.ijuri.documento.cliente.web.DocumentoDelegate;

/**
 * @author Amadeu
 *
 */
public class DiretorioForm extends BaseActionForm {

    private Long codigo;   
    private Long diretorioPaiCodigo;
    private String nome;
    private String sortKey;
       
    public Long getDiretorioPaiCodigo() {
        return diretorioPaiCodigo;
    }
    public void setDiretorioPaiCodigo(Long diretorioPaiCodigo) {
        this.diretorioPaiCodigo = diretorioPaiCodigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getSortKey() {
        return sortKey;
    }
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
    
    public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, DocumentoDelegate documentoDelegate) throws Exception {
        ITree tree = new Tree();
        tree.setSingleSelectionMode(true);

        ArrayList pastas = (ArrayList)documentoDelegate.findAllArvoreDiretorio();
        //Tira a raiz da da lista
        DiretorioVO dirRoot = (DiretorioVO)pastas.remove(0);
        TreeNode pastaRoot = new TreeNode(dirRoot.getCodigo().toString(), dirRoot.getNome(),"Pasta");
        tree.setRoot(pastaRoot);
        
        if (pastas.size() == 0){
            pastaRoot.setType("RootVazia");
        }
        
        ITreeNode pasta = null;
        ITreeNode pastaPai = null;
        String codigoPai = null;
        for (Iterator iter = pastas.iterator(); iter.hasNext();) {
            DiretorioVO dir = (DiretorioVO) iter.next();
            pasta = new TreeNode(dir.getCodigo().toString(), dir.getNome(),"Pasta");
            if (!dir.getCodigo().toString().equals(codigoPai)){
                codigoPai = dir.getParent().getCodigo().toString();
                pastaPai = tree.findNode(codigoPai);
            }
            pastaPai.addChild(pasta);
        }
        
        request.setAttribute("treeBase64",Base64Encoder.encode(tree));
        
        request.setAttribute("tree.model", tree);
    }
       
}
 