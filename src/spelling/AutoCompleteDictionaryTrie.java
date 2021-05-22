package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		/*if(word.isEmpty()&&size==0){
			System.out.println("Returned false");
			return false;
		}*/
		word=word.toLowerCase();
		char[] ch=word.toCharArray();
		TrieNode temp=root;
		for(int i=0;i<ch.length;i++){
			if(temp.getChild(ch[i])==null)temp.insert(ch[i]);
			temp=temp.getChild(ch[i]);
		}
		if(temp.endsWord())return false;
		else {
			temp.setEndsWord(true);
			size++;
			return true;
		}
	}
	
	public int size(){
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s.isEmpty()||size==0){
			//System.out.println("Returned false");
			return false;
		}
		String word=s.toLowerCase();
		char[] ch=word.toCharArray();
		TrieNode curr=root;
		for(int i=0;i<ch.length;i++){
			curr=curr.getChild(ch[i]);
			if(curr==null)return false;
			//System.out.println(curr.getText());
		}
		//System.out.println("Final word: "+curr.getText()+" Expected word: "+word);
		if(curr.getText().equals(word)){
			//System.out.println("They are equal");
			return true;
		}
//		System.out.println("They are not equal");
		return false;
	}

    @Override
     public List<String> predictCompletions(String prefix, int numCom) 
     {
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	List<String>list= new ArrayList<String>();
    	prefix=prefix.toLowerCase();
    	TrieNode tn=root;
    	Queue<TrieNode>q=new LinkedList<TrieNode>();
    	boolean stem=true;
    	//System.out.println("Prefix: "+prefix);
    	
    	for(int i=0;i<prefix.length();i++){
    		Set<Character>nexts=tn.getValidNextCharacters();
    		//System.out.println(nexts);
    		char c=prefix.charAt(i);
    		if(nexts.contains(c)){
    			tn=tn.getChild(c);
    			//System.out.println(c+" is the character, "+tn.getText()+" is the text");
    			//printNode(tn);
    		}
    		else {
    			//System.out.println("Stem not found");
    			stem=false;break;    			
    		}
    	}
    	if(stem==false)return list;
    	//System.out.println("Got the trieNode: "+tn.getText());
    	
    	
    	q.add(tn);
    	while(!q.isEmpty() && list.size()<numCom){
    		TrieNode curr= q.remove();
    		if(curr!=null){
    			Set<Character>nexts=curr.getValidNextCharacters();
    			for(char c: nexts){
    				q.add(curr.getChild(c));
   					//System.out.print(curr.getChild(c).getText()+"\t");
    			}
    			if(curr.endsWord())list.add(curr.getText());
    		}
    	}
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 for(String s:list)System.out.print(s+"\t");
         return list;
     }
	
	
	
 	// For debugging 
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	public static void main(String a[]){
 		String dictFile = "data/words.small.txt"; 
 		AutoCompleteDictionaryTrie emptyDict; 
 		AutoCompleteDictionaryTrie smallDict;
 		AutoCompleteDictionaryTrie largeDict;
 		
		emptyDict = new AutoCompleteDictionaryTrie();
		smallDict = new AutoCompleteDictionaryTrie();
		largeDict = new AutoCompleteDictionaryTrie();

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		
		DictionaryLoader.loadDictionary(largeDict, dictFile);
		
		//System.out.println("Size: "+emptyDict.size()+", "+smallDict.size()+", "+largeDict.size());
		
		emptyDict.isWord("");
		
		smallDict.predictCompletions("",4);
		//smallDict.printTree();

 	}

	
}