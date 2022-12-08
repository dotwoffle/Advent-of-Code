package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**Day 7 challenge: <a href="https://adventofcode.com/2022/day/7">No Space Left on Device</a>*/
public class Day7Challenge extends Challenge {

    /*Directory Class*/

    /**Simple class to store a directory and the files/directories it contains.*/
    private class Directory {

        /*Variables*/

        /**The name of this directory.*/
        private String name;
        /**The parent of this directory.*/
        private Directory parent;
        /**The subdirectories in this directory.*/
        private ArrayList<Directory> subdirs;
        /**The known sizes of the subdirectories.*/
        private ArrayList<Integer> subdirSizes;
        /**The sizes of the non-directory files in this directory.*/
        private ArrayList<Integer> fileSizes;


        /*Constructor*/

        public Directory(String name, Directory parent) {

            //init

            this.name = name;
            this.parent = parent;
            this.subdirs = new ArrayList<>();
            this.subdirSizes = new ArrayList<>();
            this.fileSizes = new ArrayList<>();

        }


        /*Methods*/

        /**Calculates the total size of this directory.
         * @return The total size of all files and subdirectories in this directory.
        */
        public int getSize() {

            int totalSize = 0; //total size of this directory

            //get size of files
            for(int file : fileSizes) {
                totalSize += file;
            }

            //get sizes of subdirs
            for(int i = 0; i < subdirs.size(); i++) {

                //use precalculated size if available
                if(subdirSizes.get(i) != -1) {
                    totalSize += subdirSizes.get(i);
                }

                //otherwise calculate size
                else {

                    int size = subdirs.get(i).getSize(); //get size

                    totalSize += size;
                    subdirSizes.set(i, size); //save calculated size

                }

            }

            return totalSize;

        }

        /**Adds a file to this directory.
         * @param fileSize The size of the file to add.
        */
        public void addFile(int fileSize) {
            fileSizes.add(fileSize);
        }

        /**Adds a subdirectory to this directory.
         * @param subdir The directory to add.
        */
        public void addSubdir(Directory subdir) {
            subdirs.add(subdir);
            subdirSizes.add(-1);
        }

        /**Finds the subdirectory with the given name.
         * @return The subdirectory with the given name, or null if there is none.
        */
        public Directory getSubdirectory(String subdirName) {

            for(Directory subdir : subdirs) {
                if(subdir.name.equals(subdirName)) {
                    return subdir;
                }
            }

            return null;

        }

        /**Returns the subdirectories in this directory.*/
        public ArrayList<Directory> getSubdirs() {
            return subdirs;
        }

    }


    /*Variables*/

    /**The root of the directory tree.*/
    private Directory root;


    /*Constructor*/

    public Day7Challenge() throws FileNotFoundException, IOException {

        super(2022, 7);

        //init

        this.root = new Directory("root", null);

        buildDirectoryTree();

    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        System.out.println(calculateTotalSize(root));
        
    }

    @Override
    protected void challengePart2() {
        
        int freeSpace = 70000000 - root.getSize(); //get free space
        int requiredSpace = 30000000 - freeSpace; //get space required to delete
        ArrayList<Integer> sizes = getDirSizes(root); //get sizes of all directories
        Collections.sort(sizes); //sort list

        //find smallest size that satisfies space requirement
        for(int size : sizes) {
            if(size >= requiredSpace) {
                System.out.println(size);
                break;
            }
        }
        
    }

    @Override
    protected void initialize() {}

    /**Builds the directory tree from the input file.*/
    private void buildDirectoryTree() {

        Directory currentDir = root; //cwd

        //build tree
        for(String line : input) {

            //skip first line
            if(line.equals("$ cd /")) {
                continue;
            }

            String[] lineParts = line.split(" "); //split line

            //ls listing
            if(!lineParts[0].equals("$")) {

                //dir
                if(lineParts[0].equals("dir")) {
                    currentDir.addSubdir(new Directory(lineParts[1], currentDir));
                }

                //file
                else {
                    currentDir.addFile(Integer.parseInt(lineParts[0]));
                }

            }

            //cd
            else if(lineParts[1].equals("cd")) {

                //change to parent directory
                if(lineParts[2].equals("..")) {
                    currentDir = currentDir.parent;
                }

                //otherwise change to child directory
                else {
                    currentDir = currentDir.getSubdirectory(lineParts[2]);
                }

            }

        }

    }

    /**Counts the total size of all directories in the tree that are <= 100k in size.*/
    private int calculateTotalSize(Directory root) {

        int totalSize = 0;
        int rootSize = root.getSize(); //get size for root

        totalSize += rootSize <= 100000 ? rootSize : 0; //constrain to under 100k

        //size for subdirs
        for(Directory subdir : root.getSubdirs()) {
            totalSize += calculateTotalSize(subdir);
        }

        return totalSize;

    }

    /**Gets the sizes of each directory in the tree.*/
    private ArrayList<Integer> getDirSizes(Directory root) {

        ArrayList<Integer> sizes = new ArrayList<>(); //sizes of all dirs

        sizes.add(root.getSize());

        for(Directory subdir : root.getSubdirs()) {
            for(int size : getDirSizes(subdir)) {
                sizes.add(size);
            }
        }

        return sizes;

    }
    
}
