package src.gerenciadores;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import src.modelos.MaterialBB;


public class GerenciadorBiblioteca {

    private Map<Long, MaterialBB> material = new HashMap<>();
    public GerenciadorBiblioteca() {}

    public boolean adicionarMaterial(MaterialBB materialBB) {
        if(material.containsKey(materialBB.getIdMaterial())) {
            return false; // Material já existe
        }
        material.put(materialBB.getIdMaterial(), materialBB);
        return true; // Material adicionado com sucesso
    }

    public MaterialBB getMaterialPorId(long id) {
        return material.get(id);
    }

    public List<MaterialBB> getTodosMateriais() {
        List<MaterialBB> materiais = new ArrayList<>(material.values());
        for (MaterialBB materialBB : materiais) {
            if (!materialBB.isAtivo()) {
                materiais.remove(materialBB);
            }
        }
        return materiais;
    }

    public void ListarMateriais() {
        for (MaterialBB mat : material.values()) {
            if(!mat.isAtivo()) {
                continue;
            }
            System.out.println("ID: " + mat.getIdMaterial() + ", Título: " + mat.getTitulo() + ", Idioma: " + mat.getIdioma() + ", Exclusivo VIP: " + mat.isExclusivoVip() + ", Disponível: " + mat.isDisponivel());
        }
    }

    public void removerMaterialPorId(long id) {
        MaterialBB m = material.get(id);
        m.setAtivo(false);
        m.setDisponivel(false);
    }

    public int getQuantidadeMateriais() {
        int count = 0;
        for (MaterialBB mat : material.values()) {
            if (mat.isAtivo()) {
                count++;
            }
        }
        return count;
    }

    public List<MaterialBB> getMateriaisExclusivosVip() {
        List<MaterialBB> exclusivosVip = new ArrayList<>(material.values());
        for (MaterialBB m : exclusivosVip) {
            if (!m.isExclusivoVip() || !m.isAtivo()) {
                exclusivosVip.remove(m);
            }
        }
        return exclusivosVip;
    }

    public Map<Long, MaterialBB> getMateriaisDisponiveis() {
        Map<Long, MaterialBB> disponiveis = new HashMap<>();
        for (Map.Entry<Long, MaterialBB> entry : material.entrySet()) {
            if (entry.getValue().isDisponivel()) {
                disponiveis.put(entry.getKey(), entry.getValue());
            }
        }
        return disponiveis;
    }

    
}
