import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of Hillbilly units.
 * 
 * @author 	Joris Schrauwen, Wim Schmitz
 * 
 * @invar 	Each unit can have its position as position.
 * 			| isValidPosition(this.getPosition())
 * @invar  	Each unit can have its name as name.
 * 		   	| canHaveAsName(this.getName())
 *
 */
public class Unit {
	
	/**
	 * Initialize this new unit with given position, name, weight, 
	 * strength, agility and toughness.
	 * 
	 * @param	position
	 * 			The default position for this new unit.
	 * @param	name
	 * 			The name for this new unit.
	 * @param	weight
	 * 			The weight for this new unit.
	 * @param	strength
	 * 			The strength for this new unit.
	 * @param 	agility
	 * 			The agility for this new unit.
	 * @param	toughness
	 * 			The toughness for this new unit.
	 * @post	The position of this new unit is the given position.
	 * 			| new.getPosition() == position
	 * @post    The name of this new unit is equal to the given name.
	 *        	| new.getName() == name
	 * @throws	OutOfBoundsException
	 * 			The given position is out of bounds.
	 * 			| ! isValidPosition(position)
	 * @throws  IllegalArgumentException
	 *         	This new unit cannot have the given name as its name.
	 *       	| ! canHaveAsName(this.getName())
	 */
	public Unit(double[] position, String name, int weight, int strength, 
			int agility, int toughness, float orientation) 
					throws OutOfBoundsException, IllegalArgumentException {
		if (!isValidPosition(position))
			throw new OutOfBoundsException(position);
		
		if (!canHaveAsName(name))
			throw new IllegalArgumentException(name);
		
		this.position = position;	
		this.name = name;
		this.weight = weight;
		this.strength = strength;
		this.agility = agility;
		this.toughness = toughness;
		this.orientation = orientation;
	}
	
	/**
	 * Variable registering the position of this unit.
	 */
	private double[] position;
	
	/**
	 * Variable registering the name of this unit.
	 */
	private String name;
	
	/**
	 * Variable registering the weight of this unit.
	 */
	private int weight;
	
	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength;
	
	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;
	
	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;
	
	/**
	 * Variable registering the orientation of this unit.
	 */
	private float orientation;
	
	/**
	 * Variable registering the lower bound for the x, y and z
	 * dimensions of the generated world.
	 */
	public static int lowerBound = 0;
	
	/**
	 * Variable registering the upper bound for the x, y and z
	 * dimensions of the generated world.
	 */
	public static int upperBound = 50;
	
	/**
	 * Return the position of this unit.
	 */
	@Basic
	public double[] getPosition(){
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for a unit.
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if all doubles of the given position
	 * 			are larger than or equal to the lowerbound and smaller 
	 * 			than or equal to the upperbound.
	 * 			result == (
	 */
	public static boolean isValidPosition(double[] position){
		for (int i = 0; i < position.length;)
			if ((position[i] < lowerBound) || (position[i] > upperBound))
				return false;
		return true;
	}
		
	/**
	 * Return the name of this unit.
	 */
	@Basic @Raw @Immutable
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether this unit can have the given name as its name.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsName(String name) {
		if (name.length() < 2)
			return false;
		
		char c = name[0];
		else if 
		return true;
	}

	
	/**
	 * Return the position of the cube occupied by this unit.
	 */
	public int[] getCube(){
		int[] cubeposition = new int[this.position.length];
		for (int i = 0; i < cubeposition.length; ++i)
		    cubeposition[i] = (int) this.position[i];
		return cubeposition;
	}
	/**
	 * Return the weight of this unit
	 */
	@Basic
	public int getWeight(){
		return this.weight;
	}
	/**
	 * Set the weight of this unit to the given weight
	 * 
	 * @param weight
	 * The new weight for this unit
	 * @post
	 * If the specified weight is a positive integer between 1 and 200, inclusivly,
	 * and the given weight is equal or larger than 0.5 times the sum of the units strength and agility,
	 * the new weight of this unit is equal to the given weight
	 */
	private void setWeight(int weight){
		if( (weight >= 1) && (weight <=200) && (weight>=(0.5*this.getAgility()+this.getStrength())))
			this.weight = weight;
	}
	
	
	/**
	 * Return the strength of this unit
	 */
	@Basic
	public int getStrength(){
		return this.strength;
	}
	/**
	 * Set the new strength of this unit to the given strength
	 * @param strength
	 * The new strength for this unit
	 * @post
	 * If the specified strength is a positive integer between 1 and 200, inclusivly,
	 * and the given strength is smaller than the sum of two times the units weight and its agility,
	 * the new strength of this unit is equal to the given strength
	 */
	private void setStrength(int strength){
		if( (strength >= 1) && (strength <=200) && (strength<=(this.getAgility()+2*this.getWeight())))
			this.strength = strength;
	}
	
	
	/**
	 * Return the agility of this unit
	 */
	@Basic
	public int getAgility(){
		return this.agility;
	}
	/**
	 * Set the new agility of this unit to the given agility
	 * @param agility
	 * The new agility for this unit
	 * @post
	 * If the specified agility is a positive integer between 1 and 200, inclusively,
	 * and the given agility is smaller than the sum of two times the units weight and its strength,
	 * the new agility of this unit is equal to the given agility
	 */
	private void setAgility(int agility){
		if( (agility >= 1) && (agility <=200) && (agility<=(this.getStrength()+2*this.getWeight())))
			this.agility = agility;
	}
	
	/**
	 * Return the toughness of this unit
	 */
	@Basic
	public int getToughness (){
		return this.toughness;
	}
	/**
	 * Set the new toughness of this unit to the given toughness
	 * @param toughness
	 * The new toughness for this unit
	 * @post
	 * If the specified toughness is a positive integer between 1 and 200, inclusively,
	 * the new agility of this unit is equal to the given agility
	 */
	private void setToughness(int toughness){
		if( (toughness >= 1) && (toughness <=200))
			this.toughness = toughness;
	}
	
	/**
	 * inspect the current orientation of this unit
	 */
	@Basic
	public float getOrientation (){
		return this.orientation;
	}
	/**
	 * Change the orientation of this unit to the specified angle
	 * @param orientation
	 * The new angle of orientation for this unit
	 * @post
	 * If the specified angle is a floating number between 0 and 2*PI, inclusively,
	 * the orientation of this unit will be changed to the specified angle
	 */
	private void setOrientation(float angle){
		if( (angle >= 0) && (angle <=2*Math.PI))
			this.orientation = angle;
		
	}

}

