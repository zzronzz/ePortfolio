from pymongo import MongoClient
from pymongo.errors import ConnectionFailure, OperationFailure, PyMongoError


class AnimalShelter:
    """CRUD operations for the animals collection in MongoDB."""

    def __init__(
        self,
        username,
        password,
        host="localhost",
        port=27017,
        database="aac",
        collection="animals"
    ):
        """
        Initializes the MongoDB client and connects to the selected database
        and collection.

        Args:
            username (str): MongoDB username.
            password (str): MongoDB password.
            host (str): MongoDB host name.
            port (int): MongoDB port number.
            database (str): MongoDB database name.
            collection (str): MongoDB collection name.
        """
        self.client = None
        self.database = None
        self.collection = None

        try:
            if not username or not password:
                raise ValueError("Username and password are required.")

            self.client = MongoClient(
                f"mongodb://{username}:{password}@{host}:{port}",
                serverSelectionTimeoutMS=5000
            )

            self.client.admin.command("ping")

            self.database = self.client[database]
            self.collection = self.database[collection]

        except (ConnectionFailure, OperationFailure, ValueError) as error:
            print(f"Database connection error: {error}")

    def create(self, data):
        """
        Inserts a document into the animals collection.

        Args:
            data (dict): Document to insert.

        Returns:
            bool: True if insert succeeds, False otherwise.
        """
        if not self._collection_ready():
            return False

        if not isinstance(data, dict) or not data:
            print("Create failed: data must be a non-empty dictionary.")
            return False

        try:
            result = self.collection.insert_one(data)
            return result.acknowledged

        except PyMongoError as error:
            print(f"Create operation failed: {error}")
            return False

    def read(self, query=None):
        """
        Reads documents from the animals collection.

        Args:
            query (dict): MongoDB query filter. Defaults to an empty query.

        Returns:
            list: List of matching documents.
        """
        if not self._collection_ready():
            return []

        if query is None:
            query = {}

        if not isinstance(query, dict):
            print("Read failed: query must be a dictionary.")
            return []

        try:
            return list(self.collection.find(query))

        except PyMongoError as error:
            print(f"Read operation failed: {error}")
            return []

    def update(self, query, new_values):
        """
        Updates documents in the animals collection.

        Args:
            query (dict): MongoDB query filter.
            new_values (dict): Fields and values to update.

        Returns:
            int: Number of modified documents.
        """
        if not self._collection_ready():
            return 0

        if not isinstance(query, dict) or not query:
            print("Update failed: query must be a non-empty dictionary.")
            return 0

        if not isinstance(new_values, dict) or not new_values:
            print("Update failed: new values must be a non-empty dictionary.")
            return 0

        try:
            result = self.collection.update_many(query, {"$set": new_values})
            return result.modified_count

        except PyMongoError as error:
            print(f"Update operation failed: {error}")
            return 0

    def delete(self, query):
        """
        Deletes documents from the animals collection.

        Args:
            query (dict): MongoDB query filter.

        Returns:
            int: Number of deleted documents.
        """
        if not self._collection_ready():
            return 0

        if not isinstance(query, dict) or not query:
            print("Delete failed: query must be a non-empty dictionary.")
            return 0

        try:
            result = self.collection.delete_many(query)
            return result.deleted_count

        except PyMongoError as error:
            print(f"Delete operation failed: {error}")
            return 0

    def _collection_ready(self):
        """
        Checks whether the MongoDB collection is available.

        Returns:
            bool: True if the collection is ready, False otherwise.
        """
        if self.collection is None:
            print("Database operation failed: collection is not available.")
            return False

        return True