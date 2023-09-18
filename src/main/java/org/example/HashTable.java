package org.example;

import java.util.LinkedList;

public class HashTable<K, V> {

    private static class Pair<K, V> {
        K chave;
        V valor;

        public Pair(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }
    private int capacidade;
    private LinkedList<Pair<K, V>>[] tabela;

    public HashTable(int capacidade) {
        this.capacidade = capacidade;
        tabela = new LinkedList[capacidade];
    }

    private int calcularHash(K chave) {
        int hashCode = chave.hashCode();
        return Math.abs(hashCode % capacidade);
    }

    public V adicionar(K chave, V valor) {
        int indice = calcularHash(chave);
        if (tabela[indice] == null) {
            tabela[indice] = new LinkedList<>();
        }
        for (Pair<K, V> pair : tabela[indice]) {
            if (pair.chave.equals(chave)) {
                V valorAntigo = pair.valor;
                pair.valor = valor;
                return valorAntigo;
            }
        }

        tabela[indice].add(new Pair<>(chave, valor));
        return valor;
    }

    public V buscar(K chave) {
        int indice = calcularHash(chave);
        if (tabela[indice] != null) {
            for (Pair<K, V> pair : tabela[indice]) {
                if (pair.chave.equals(chave)) {
                    return pair.valor;
                }
            }
        }
        return null; // Chave não encontrada
    }

    public boolean contem(K chave){
        int indice = calcularHash(chave);
        if(tabela[indice] != null){
            for(Pair<K, V> pair: tabela[indice]){
                if(pair.chave.equals(chave)){
                    return true;
                }
            }
        }
        return false;
    }

    public void remover(K chave) {
        int indice = calcularHash(chave);
        if (tabela[indice] != null) {
            tabela[indice].removeIf(pair -> pair.chave.equals(chave));
        }
    }

    public void imprimirTabela() {
        for (int i = 0; i < capacidade; i++) {
            if (tabela[i] != null) {
                for (Pair<K, V> pair : tabela[i]) {
                    System.out.println("Chave: " + pair.chave + ", Valor: " + pair.valor);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> tabelaHash = new HashTable<>(100);

        tabelaHash.adicionar("chave1", 42);
        tabelaHash.adicionar("chave2", 17);
        tabelaHash.adicionar("chave3", 99);

        System.out.println("Valor da chave1: " + tabelaHash.buscar("chave1"));
        System.out.println("Valor da chave2: " + tabelaHash.buscar("chave2"));
        System.out.println("Valor da chave2: " + tabelaHash.buscar("chave3"));

        tabelaHash.remover("chave3");

        System.out.println("contém chave1?: " + tabelaHash.contem("chave1"));
        System.out.println("contém chave2?: " + tabelaHash.contem("chave2"));
        System.out.println("contém chave3?: " + tabelaHash.contem("chave3"));

        System.out.println("Elemento substituido: " + tabelaHash.adicionar("chave1", 45));

        tabelaHash.imprimirTabela();
    }
}
