import java.util.Map;
import java.util.HashMap;

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

    public Map<Long, MaterialBB> getTodosMateriais() {
        return material;
    }

    public void removerMaterialPorId(long id) {
        material.remove(id);
    }

    public int getQuantidadeMateriais() {
        return material.size();
    }

    public Map<Long, MaterialBB> getMateriaisExclusivosVip() {
        Map<Long, MaterialBB> exclusivosVip = new HashMap<>();
        for (Map.Entry<Long, MaterialBB> entry : material.entrySet()) {
            if (entry.getValue().isExclusivoVip()) {
                exclusivosVip.put(entry.getKey(), entry.getValue());
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
