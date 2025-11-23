package src.gerenciadores;

import src.modelos.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GerenciadorInscricoes {
    Map<Long, LinkedHashSet<Aluno>> preInscricoesVip = new HashMap<>();
    Map<Long, LinkedHashSet<Aluno>> preInscricoesNormais = new HashMap<>();

    public void adicionarInscricao(Aluno a, Curso c){
        if (a.isVip()){
            preInscricoesVip.computeIfAbsent(c.getId(), k -> new LinkedHashSet<>()).add(a);
        }else { preInscricoesNormais.computeIfAbsent(c.getId(), k -> new LinkedHashSet<>()).add(a); }
    }

    public boolean removerInscricao(Aluno a, Curso c){
        if(preInscricoesVip.computeIfAbsent(c.getId(), k-> new LinkedHashSet<>()).contains(a)){
            preInscricoesVip.get(c.getId()).remove(a);
            return true;
        } else{
            if(preInscricoesNormais.computeIfAbsent(c.getId(),k -> new LinkedHashSet<>()).contains(a)){
                preInscricoesNormais.get(c.getId()).remove(a);
                return true;
            }
        }
        return false;
    }

    public void tornarVip(Aluno a){
        for (Long idCurso : preInscricoesNormais.keySet()) {
            if (preInscricoesNormais.get(idCurso).contains(a)){
                preInscricoesVip.computeIfAbsent(idCurso, k -> new LinkedHashSet<>()).add(a);
                preInscricoesNormais.get(idCurso).remove(a);
            }
        }
    }

    public void tornarNormal(Aluno a){
        for (Long idCurso : preInscricoesVip.keySet()) {
            if (preInscricoesVip.get(idCurso).contains(a)){
                preInscricoesNormais.computeIfAbsent(idCurso, k -> new LinkedHashSet<>()).add(a);
                preInscricoesVip.get(idCurso).remove(a);
            }
        }
    }

    public List<Aluno> puxarProximos(int n, Curso c) {
        List<Aluno> proximos = new ArrayList<>();
        if (n <= 0) return proximos;

        long id = c.getId();

        LinkedHashSet<Aluno> vipSet = preInscricoesVip.computeIfAbsent(id, k-> new LinkedHashSet<>());
        LinkedHashSet<Aluno> normalSet = preInscricoesNormais.computeIfAbsent(id, k-> new LinkedHashSet<>());

        int count = 0;


        if (vipSet.size() <= n) {
            // pega tudo
            proximos.addAll(vipSet);
            count = vipSet.size();
            vipSet.clear();
        } else {
            // pega só até n
            Iterator<Aluno> it = vipSet.iterator();
            while (it.hasNext() && count < n) {
                Aluno a = it.next();
                proximos.add(a);
                it.remove();
                count++;
            }
        }

        if (count >= n) {
            return proximos;
        }

        int faltam = n - count;

        if (normalSet.size() <= faltam) {
            proximos.addAll(normalSet);
            normalSet.clear();
        } else {
            Iterator<Aluno> it2 = normalSet.iterator();
            int puxados = 0;

            while (it2.hasNext() && puxados < faltam) {
                Aluno a = it2.next();
                proximos.add(a);
                it2.remove();
                puxados++;
            }
        }

        return proximos;
    }
        
    
}
