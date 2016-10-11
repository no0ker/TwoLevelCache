package test.com.Impl;

import test.com.Api.Cache;

import java.io.*;

public class HddCache<K, V> implements Cache<K, V> {

    private String path;

    public HddCache(String path) {
        this.path = path;
        testDir(path);
    }

    public void put(K key, V value) throws IOException {
        FileOutputStream fos = null;
        fos = new FileOutputStream(makeFilePath(key));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(value);
        oos.flush();
        oos.close();
    }

    public V get(K key) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(makeFilePath(key));
        ObjectInputStream oin = new ObjectInputStream(fis);
        V result = (V) oin.readObject();
        oin.close();
        return result;
    }

    public boolean contains(K key) {
        File file = new File(makeFilePath(key));
        return file.isFile();
    }

    public void remove(K key) {
        File file = new File(makeFilePath(key));
        file.delete();
    }

    private String makeFilePath(K key){
        if("\\".equals(path.substring(path.length()-1))){
            testDir(path + key.hashCode());
            return path + key.hashCode() + "\\" + key.toString();
        } else {
            testDir(path + "\\" + key.hashCode());
            return  path + "\\" + key.hashCode() + "\\" + key.toString();
        }
    }

    private void testDir(String dirName){
        File dir = new File(dirName);
        if(!dir.exists() || !dir.isDirectory()){
            dir.mkdir();
        }
    }
}
