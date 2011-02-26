package cnauroth.dumpservlet

/**
 * Common trait for objects that expose properties as key-value pairs.
 */
private[dumpservlet] trait PropertyProvider {

  /**
   * Returns a mapping containing keys that are String property names and values
   * that are functions that evaluate and return the property value.
   *
   * @return mapping of String property names to functions that evaluate and
   *   return those property values.
   */
  protected def properties: Map[String, () => Any]

  /**
   * Evaluates and returns the value of the property with the given name.
   *
   * @return value of the property with the given name.
   */
  final def getProperty(name: String) = this.properties.get(name).getOrElse(
    () => null).apply

  /**
   * Returns an iterator over all property names.
   *
   * @return iterator over all property names.
   */
  final def getPropertyNames() = properties.keySet.iterator
}

