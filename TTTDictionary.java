/**
 * Class creates a TTTDictionary and has accompany methods to interact with it
 * 
 * @author Michael Booth
 */
public class TTTDictionary implements TTTDictionaryADT {

	private int sizeOfList; // Size of the HashMap
	private HashList HashDict[]; // Stores the HashLists

	/*
	 * Constructor creates a HashMap of the specified size
	 */
	public TTTDictionary(int size) {
		sizeOfList = size;
		HashDict = new HashList[sizeOfList];
	}

	/*
	 * Puts the specified TTTRecord into a HashList, throws an expection if
	 * already in
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException {
		int configNumber = nodeHashing(record.getConfiguration());

		if (HashDict[configNumber] != null) {
			int returnNumber = HashDict[configNumber].add(new HashNode(record));
			if (returnNumber == 1) {
				throw new DuplicatedKeyException();
			} else {
				return 1;
			}
		} else {
			HashDict[configNumber] = new HashList();
			HashDict[configNumber].add(new HashNode(record));
			return 0;
		}
	}

	/*
	 * Removes the specified config from the list. Throws an exception if it's
	 * not in
	 */
	public void remove(String config) throws InexistentKeyException {
		int hashedConfig = nodeHashing(config);
		if (HashDict[hashedConfig] == null) {
			throw new InexistentKeyException();
		} else {
			HashDict[hashedConfig].remove(config);
		}
	}

	/*
	 * Returns the TTTRecord that holds the specified config
	 */
	public TTTRecord get(String config) {
		int hashedConfig = nodeHashing(config);
		if (HashDict[hashedConfig] == null) {
			return null;
		} else {
			return HashDict[hashedConfig].getRecord(config);
		}

	}

	/*
	 * returns the size of the list
	 */
	public int numElements() {
		return sizeOfList;
	}

	/*
	 * Hashes the config into a unique number
	 */
	private int nodeHashing(String configeration) {
		String config = configeration;
		int configNumber = 0;
		for (int i = 0; i < config.length(); i++) {
			char c = config.charAt(i);
			configNumber += (int) c * i * i;
		}

		return configNumber % sizeOfList;

	}

}
