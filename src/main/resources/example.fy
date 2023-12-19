
# All files have only one statement.
# Whatever that statement returns will be the
#   value associated with the file.

# Curly brace makes a directory value.
# It is completely static and cannot be modified.
# It is returned when using the import function.

# Packages are arranged like this:
#   {ident: VALUE, ident: VALUE, ...}

{

  pies: 
      (pi = import("pi"), pi.pi, pi.tau)!
  # The ! returns removes all null values from
  #   the tuple.
  # Since assignments return null, it will remove
  #   that null and return the tuple with just
  #   pi and tau.

  last_test:
      ("A", "B", "C")[-1]
  # If you only intend to use one of the values,
  #   you can just index the value you want.

  get_last_char: 
      char fun(in: string)
          in[-1]
  # Example of a function.
  # Now, file_name.get_last_char("Hello") will
  #   return 'o'
  
}