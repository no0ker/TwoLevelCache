package test.com.Impl;

import test.com.Api.Cache;

import java.io.*;

public class HddCache<K, V extends Serializable> implements Cache<K, V> {

    public final static String SEPARATOR = "\\";

    private final String path;

    public HddCache(String path) {
        if ("\\".equals(path.substring(path.length() - 1))) {
            this.path = path.substring(0, path.length() - 1);
        } else {
            this.path = path;
        }
        File targetDir = new File(path);
        if(targetDir.exists() && targetDir.isDirectory()){
            clear();
        }
    }

    public void put(K key, V value) throws IOException {
        FileOutputStream fos = null;
        fos = new FileOutputStream(makeFilePath(key));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(value);
        oos.flush();
        oos.close();
    }

    public V get(K key) throws ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(makeFilePath(key));
            ObjectInputStream oin = new ObjectInputStream(fis);
            V result = (V) oin.readObject();
            oin.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean contains(K key) {
        File file = new File(makeFilePath(key));
        return file.isFile();
    }

    public void remove(K key) {
        File file = new File(makeFilePath(key));
        file.delete();
    }

    public void clear() {
        File parentDir = new File(path);

        for (String dirName : parentDir.list()) {
            File dir = new File(path + SEPARATOR + dirName);
            for (String fileName : dir.list()) {
                File file = new File(path + SEPARATOR + dirName + SEPARATOR + fileName);
                file.delete();
            }
            dir.delete();
        }
    }

    private String makeFilePath(K key) {
        createDirIfNotExists(path + SEPARATOR + key.hashCode());
        return path + SEPARATOR + key.hashCode() + SEPARATOR + key.toString();
    }

    private void createDirIfNotExists(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
    }
}
